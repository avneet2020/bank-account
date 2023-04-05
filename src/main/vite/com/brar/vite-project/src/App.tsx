import Login from "./components/Login";
import LoginBody from "./interfaces/LoginBody";
import "./App.css";
import { useState } from "react";
import Welcome from "./components/Welcome";

function App() {
  const [loginInfo, setLoginInfo] = useState<LoginBody>();
  const handleOnClick = (result: LoginBody) => {
    setLoginInfo(result);
  };
  return (
    <>
      <h1 className="text-center welcome">Welcome to the Bank</h1>
      <Login onClick={handleOnClick}></Login>
      {loginInfo !== undefined && <Welcome userInfo={loginInfo}></Welcome>}
    </>
  );
}

export default App;
