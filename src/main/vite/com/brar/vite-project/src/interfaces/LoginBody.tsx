export default interface User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  balance: number;
  user: {
    id: number;
    username: string;
    password: string;
  };
}
