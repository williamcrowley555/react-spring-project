import axios from "axios";

const USER_API_BASE_URL = "http://localhost:8082/create-web-service/api/users";

class UserService {
    getUserByUserId(userId) {
        return axios.get(USER_API_BASE_URL + "/" + userId);
    }

    getUserByUserRoleName(roleName) {
        return axios.get(USER_API_BASE_URL + "/roles/"+ roleName);
    }

    getUsers(page, limit) {
        return axios.get(USER_API_BASE_URL + "?page=" + page + "&lmit=" +limit);
    }

    addUser(userData){
        return axios.post(USER_API_BASE_URL, userData);
    }

    deleteUser(userId){
        return axios.delete(USER_API_BASE_URL + "/" + userId);
    }

    updateUser(userData){
        return axios.put(USER_API_BASE_URL + '/' + userData.userId, userData);
    }
}

export default new UserService();