import { User } from "./User";

export interface Patient extends User{
    birthDate:number[];
    weight:number;
}

export interface PatientMonitoring extends Patient{
    monitoringValue: number
}

