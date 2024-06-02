import customAxios from "../AxiosInterceptor/AxiosInterceptor";

class SymptomService {
    findAll() {
      return customAxios.get(`/symptom`);
    }
  
  }
  
  export default new SymptomService();