export interface Ingredient{
    id:number;
    name:string;
}

export interface AddAllergen{
    patientId:number | undefined;
    allergens:number[];
}