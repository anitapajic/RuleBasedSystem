export interface newAnamnesis{
    patientEmail:string;
    patientsSymptomsIds:number[];
    description:string;
    isTestNeeded:boolean;
    doctorId:number | undefined;
}