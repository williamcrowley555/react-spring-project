import axios from "axios";

const USER_API_BASE_URL = "http://localhost:8082/create-web-service/api/users";

class UserService {
    getUserByUserId(userId) {
        return axios.get(USER_API_BASE_URL + "/" + userId);
    }

    getUsers(page, limit) {
        return axios.get(USER_API_BASE_URL + "?page=" + page + "&lmit=" +limit);
    }
}

export default new UserService();