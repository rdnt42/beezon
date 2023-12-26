import axios from "axios";


export const StorageManagerService = {
    getOrder(id: string): Promise<any> {
        return axios.get<any[]>(`/api/v1/orders/${id}`)
            .then(res => {
                return res.data;
            });
    },
}