import Role from "./enums/Role";

export interface User {
  id: number;
  name: string;
  surname: string;
  email: string;
  password: string;
  picture : string;
  role: Role;
}

export interface NewUser {
  name: string;
  surname: string;
  email: string;
  password: string;
  confPassword : string;
  role : Role;
  //picture : string;
}
export interface LoginUser{
  id: number;
  token: string;
  role: Role;
}

export interface ChangePassword{
  username: string;
  password: string;
  confPassword : string;
}