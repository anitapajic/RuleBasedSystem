import { Disease } from "./Disease";
import { Patient } from "./Patient";

export interface Diagnosis{
    id:number;
    patient:Patient;
    disease:Disease;
}