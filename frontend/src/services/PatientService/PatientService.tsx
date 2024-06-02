import { AddAllergen } from "../../models/Ingredient";
import customAxios from "../AxiosInterceptor/AxiosInterceptor";

class PatientService {
    findAll() {
      return customAxios.get(`/patient`);
    }
    addAllergens(addAllergen: AddAllergen){
      return customAxios.put('/patient/allergens', addAllergen)
    }
  
  }
  
  export default new PatientService();