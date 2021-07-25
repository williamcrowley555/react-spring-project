import axios from "axios";

const AUTH_API_BASE_URL = "http://localhost:8082/create-web-service/api/auth";

class AuthService {
  signin(userLoginInfo,...config) {
    return axios.post(AUTH_API_BASE_URL + "/signin", userLoginInfo);
  }

  signup(userRegisterInfo) {
    return axios.post(AUTH_API_BASE_URL + "/signup", userRegisterInfo);
  }
}

export default new AuthService();
