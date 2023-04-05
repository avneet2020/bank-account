import { FormEvent } from "react";
import "./Login.css";
import LoginBody from "../interfaces/LoginBody";
interface Props {
  onClick: (result: LoginBody) => void;
}

function Login({ onClick }: Props) {
  const submitLogin = async (e: FormEvent) => {
    e.preventDefault();
    const formData = new FormData(e.target as HTMLFormElement);
    const body = {
      username: formData.get("username") as string,
      password: formData.get("password") as string,
    };

    const information = await fetch(`http://127.0.0.1:8080/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    });

    onClick(await information.json());
  };

  return (
    <div className="login">
      <form onSubmit={(e) => submitLogin(e)}>
        <div className="mb-3 text-left">
          <div className="input-group">
            <span className="input-group-text" id="inputUsername">
              Username
            </span>
            <input type="text" className="form-control" name="username" />
          </div>
          <div id="usernameHelp" className="form-text">
            We'll never share your email with anyone else.
          </div>
          <div className="input-group">
            <span className="input-group-text" id="inputPassword">
              Password
            </span>
            <input type="text" className="form-control" name="password" />
          </div>
        </div>
        <div className="d-grid gap-2">
          <button type="submit" className="btn btn-primary">
            Submit
          </button>
        </div>
      </form>
    </div>
  );
}

export default Login;
