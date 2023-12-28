import axios from "axios";
import {Order} from "../obj/Order";

export const StorageManagerService = {
    getOrder(id: string): Promise<Order> {
        return axios.get<Order>(`/api/v1/orders/${id}`)
            .then(res => {
                return res.data;
            });
    },
}