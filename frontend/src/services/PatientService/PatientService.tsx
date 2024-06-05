import { AddAllergen } from "../../models/Ingredient";
import customAxios from "../AxiosInterceptor/AxiosInterceptor";

class PatientService {
    findAll() {
      return customAxios.get(`/patient`);
    }
    addAllergens(addAllergen: AddAllergen){
      return customAxios.put('/patient/allergens', addAllergen)
    }
    findAllWithDisease(diseaseId: number){
        return customAxios.get('/patient/with-disease/' + diseaseId);
    }
  
  }
  
  export default new PatientService();