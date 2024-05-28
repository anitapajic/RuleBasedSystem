import { User } from "./User";

export interface Patient extends User{
    birthDate:number[];
    weight:number;
}