import { DateRange } from "./DateRange";
import { Diagnosis } from "./Diagnosis";
import { Medicine } from "./Medicine";

export interface Therapy{
    id:number;
    diagnosis : Diagnosis;
    medicine : Medicine;
    dateRange:DateRange;
    milligrams:number;
    frequency:number;
}