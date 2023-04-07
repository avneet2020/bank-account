import { useState } from "react";
import "./Register.css";
import LoginBody from "../interfaces/LoginBody";

interface Props {
  loggedIn: (result: LoginBody) => void;
  screenSwitch(screen: number): void;

  url: string;
}

function Register({ loggedIn, screenSwitch, url }: Props) {
  const [isSuccess, setIsSuccess] = useState(false);

  const attemptLogin = async (username: string, password: string) => {
    const logInBody = {
      username: username,
      password: password,
    };

    const logInInformation: Response = await fetch(`${url}login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(logInBody),
    }).catch((err) => {
      console.error("Error during fetch", err);
    });
    if (logInInformation instanceof Response && logInInformation.ok) {
      setIsSuccess(true);
      loggedIn(await logInInformation.json());
    } else {
      console.error("Fetch failed with error", logInInformation);
    }
  };

  const handleAccountCreation = async (e: React.FormEvent) => {
    e.preventDefault();
    const formData = new FormData(e.target as HTMLFormElement);
    const concatEmail = ((formData.get("email1") as string) +
      "@" +
      formData.get("email2")) as string;
    const username = formData.get("username") as string;
    const password = formData.get("password") as string;
    const body = {
      username: username,
      password: password,
      firstName: formData.get("first") as string,
      lastName: formData.get("last") as string,
      email: concatEmail,
    };

    const information: Response = await fetch(`${url}save`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    }).catch((err) => {
      console.error("Error during fetch", err);
    });
    if (information instanceof Response && information.ok) {
      attemptLogin(username, password);
    }
  };
  return (
    <div className="register">
      <h2>Create an Account</h2>
      <form onSubmit={(e) => handleAccountCreation(e)}>
        <div className="input-group">
          <span className="input-group-text">First and Last Name:</span>
          <input type="text" className="form-control" name="first" required />
          <input type="text" className="form-control" name="last" required />
        </div>
        <br />
        <div className="input-group">
          <span className="input-group-text">Username and Password:</span>
          <input
            type="text"
            className="form-control"
            name="username"
            required
          />
          <input
            type="password"
            className="form-control"
            name="password"
            required
          />
        </div>
        <br />
        <div className="input-group mb-3">
          <span className="input-group-text">Email:</span>

          <input type="text" className="form-control" name="email1" required />
          <span className="input-group-text">@</span>
          <input type="text" className="form-control" name="email2" required />
        </div>
        {isSuccess && (
          <div className="alert alert-success fade show" role="alert">
            Success! Redirecting...
          </div>
        )}

        <div className="d-grid gap-2">
          <button type="submit" className="btn btn-primary">
            Register
          </button>
        </div>
      </form>
      <button className="login-button" onClick={() => screenSwitch(0)}>
        Already have an account? Click Here!
      </button>
    </div>
  );
}

export default Register;
