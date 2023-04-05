import LoginBody from "../interfaces/LoginBody";

interface Props {
  userInfo: LoginBody;
}

function Welcome({ userInfo }: Props) {
  return (
    <>
      <h1>Welcome {userInfo.firstName}!</h1>

      <button>Click</button>
    </>
  );
}

export default Welcome;
