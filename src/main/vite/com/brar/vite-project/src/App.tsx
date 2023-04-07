import Login from "./components/Login";
import LoginBody from "./interfaces/LoginBody";
import "./App.css";
import { useState } from "react";
import Welcome from "./components/Welcome";
import Register from "./components/Register";

function App() {
  const BANK_URL = "http://127.0.0.1:8080/";
  // 0 = login, 1 = register, 2 = welcome
  const [screen, setScreen] = useState<number>(0);
  const [loginInfo, setLoginInfo] = useState<LoginBody>();
  const loggedIn = (result: LoginBody) => {
    if (result !== undefined) {
      setLoginInfo(result);
      setScreen(2);
    }
  };
  const handleScreenSwitch = (screen: number) => {
    setScreen(screen);
  };

  return (
    <div className="information">
      <h1 className="text-center welcome">Welcome to the Bank</h1>
      {loginInfo === undefined && screen === 0 && (
        <Login
          loggedIn={loggedIn}
          url={BANK_URL}
          screenSwitch={handleScreenSwitch}
        ></Login>
      )}
      {screen === 1 && (
        <Register
          url={BANK_URL}
          loggedIn={loggedIn}
          screenSwitch={handleScreenSwitch}
        ></Register>
      )}
      {loginInfo !== undefined && screen === 2 && (
        <Welcome userInfo={loginInfo} url={BANK_URL}></Welcome>
      )}
    </div>
  );
}

export default App;
