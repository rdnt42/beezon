import axios from "axios";
import {LoginRequest} from "../obj/LoginRequest";
import {LoginResponse} from "../obj/LoginResponse";

export const LoginService = {
    auth(request: LoginRequest): Promise<LoginResponse> {
        return axios.post<LoginResponse>(`/api/login`, request)
            .then(res => {
                return res.data;
            });
    },
}