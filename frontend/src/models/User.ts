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
  weight: number | string;
}
export interface LoginUser{
  userId: number;
  accessToken: string;
  role: Role;
  expiresIn:number;
}

export interface ChangePassword{
  username: string;
  password: string;
  confPassword : string;
}