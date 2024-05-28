import { newAnamnesis } from "../../models/Anamnesis";
import customAxios from "../AxiosInterceptor/AxiosInterceptor";

class AnamnesisService {
    diagnostic(newAnamnesis : newAnamnesis) {
      return customAxios.post(`/anamnesis`, newAnamnesis);
    }
  
  }
  
  export default new AnamnesisService();