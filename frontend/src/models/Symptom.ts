import { symptomLevel } from "./enums/SymptomLevel";

export interface Symptom{
    id : number;
    name: string;
    symptomLevel: symptomLevel
}