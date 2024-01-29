import axios from "axios";

export const StorageManagerService = {
    getOrder(id) {
        return axios.get(`/api/v1/orders/${id}`)
            .then(res => {
                return res.data;
            });
    },
    getOrCreateOrder(request) {
        return axios.post(`/api/v1/orders/get-or-create`, request)
            .then(res => {
                return res.data;
            });
    },
}