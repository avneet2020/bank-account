export default interface User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  user: {
    id: number;
    username: string;
    password: string;
  };
}
