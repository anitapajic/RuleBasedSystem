import customAxios from "../AxiosInterceptor/AxiosInterceptor";

class DiseaseService {
    findAll() {
      return customAxios.get(`/disease`);
    }
  
  }
  
  export default new DiseaseService();