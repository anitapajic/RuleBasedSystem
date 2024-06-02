import { Symptom } from "../Symptom";

export interface AnamnesisEvaluation{
    symptoms:Symptom[];
    level1Symptoms:number;
    level2Symptoms:number;
    bloodAnalysisNeeded:boolean;
    bloodAnalysisResult:boolean;
    testNeeded:boolean;
    possibleDiseaseName:string;
    isConfirmationTestNeeded:boolean;
    confirmationTestResult:boolean;
    diseaseProbability:number;
}