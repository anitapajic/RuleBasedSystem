import customAxios from "../AxiosInterceptor/AxiosInterceptor";

class TherapyService {
    diagnostic(patientEmail : string) {
      return customAxios.get(`/therapy/${patientEmail}`);
    }
    findByPatientId(id:number|undefined){
      return customAxios.get(`/therapy/all/${id}`);
    }
  
  }
  
  export default new TherapyService();