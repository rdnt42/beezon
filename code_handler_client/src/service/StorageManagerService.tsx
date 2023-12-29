import axios from "axios";
import {Order} from "../obj/Order";
import {CreateOrderRequest} from "../obj/api/CreateOrderRequest";

export const StorageManagerService = {
    getOrder(id: string): Promise<Order> {
        return axios.get<Order>(`/api/v1/orders/${id}`)
            .then(res => {
                return res.data;
            });
    },
    getOrCreateOrder(request: CreateOrderRequest): Promise<Order> {
        return axios.post<Order>(`/api/v1/orders/get-or-create`, request)
            .then(res => {
                return res.data;
            });
    },
}