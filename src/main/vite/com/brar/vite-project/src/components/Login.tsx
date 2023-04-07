import { FormEvent, useState } from "react";
import "./Login.css";
import LoginBody from "../interfaces/LoginBody";
interface Props {
  loggedIn: (result: LoginBody) => void;
  screenSwitch: (screen: number) => void;
  url: string;
}

function Login({ loggedIn, screenSwitch, url }: Props) {
  const [isLogin, setIsLogin] = useState(true);
  const [isError, setIsError] = useState(false);
  const submitLogin = async (e: FormEvent) => {
    e.preventDefault();
    const formData = new FormData(e.target as HTMLFormElement);
    const body = {
      username: formData.get("username") as string,
      password: formData.get("password") as string,
    };

    const information: Response = await fetch(`${url}login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    }).catch((err) => {
      console.error("Error during fetch", err);
    });
    if (information instanceof Response && information.ok) {
      loggedIn(await information.json());
    } else {
      setIsError(true);
      console.error("Fetch failed with error", information);
    }
  };

  return (
    <div className="login">
      <h2 className="title">Enter Your Credentials</h2>
      <form onSubmit={(e) => submitLogin(e)}>
        <div className="mb-3 text-left ">
          <div className="input-group input-group-dark">
            <span className="input-group-text" id="inputUsername">
              Username
            </span>
            <input
              type="text"
              className="form-control"
              name="username"
              required
            />
          </div>
          <br></br>
          <div className="input-group input-group-dark ">
            <span className="input-group-text" id="inputPassword">
              Password
            </span>
            <input
              type="password"
              className="form-control"
              name="password"
              required
            />
          </div>
        </div>
        <div className="d-grid gap-2">
          <button type="submit" className="btn btn-primary">
            Submit
          </button>
          {isError && (
            <div
              className="alert alert-warning alert-dismissible fade show"
              role="alert"
            >
              Error Logging In!
              <button
                type="button"
                className="btn-close"
                onClick={() => setIsError(false)}
                data-bs-dismiss="alert"
              />
            </div>
          )}
        </div>
      </form>
      <button className="register-button" onClick={() => screenSwitch(1)}>
        New user? Click to Register!
      </button>
    </div>
  );
}

export default Login;
