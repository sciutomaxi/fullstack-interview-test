import axios from 'axios';

export class BranchService {
    baseUrl = "http://localhost:8080/branches";

    getAll() {
        return axios.get(this.baseUrl).then(res => res.data);
    }

    save(branch) {
        return axios.post(this.baseUrl + "/", branch).then(res => res.data);
    }

}