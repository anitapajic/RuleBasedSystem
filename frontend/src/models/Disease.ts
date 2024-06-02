import { Symptom } from "./Symptom";
import { TestType } from "./enums/TestType";

export interface Disease{
    id:number;
    name:string;
    symptoms:Symptom[];
    testType: TestType;
}