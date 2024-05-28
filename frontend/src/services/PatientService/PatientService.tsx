import customAxios from "../AxiosInterceptor/AxiosInterceptor";

class PatientService {
    findAll() {
      return customAxios.get(`/patient`);
    }
  
  }
  
  export default new PatientService();