import { ChangePassword, NewUser } from "../../models/User";
import customAxios from "../AxiosInterceptor/AxiosInterceptor";

class UserService {
  register(userData: NewUser) {
    return customAxios.post(`/user/register`, userData);
  }
  login(userData: { email: string; password: string }) {
    return customAxios.post(`/user/login`, userData);
  }
  changePassword(userData: ChangePassword) {
    return customAxios.post(`/user/changePassword`, userData);
  }

}

export default new UserService();
