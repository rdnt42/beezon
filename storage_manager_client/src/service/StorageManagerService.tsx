import axios from "axios";
import {Order} from "../obj/Order";
import {CreateOrderRequest} from "../obj/api/CreateOrderRequest";

export const StorageManagerService = {
    async getOrder(id: string): Promise<Order> {
        const res = await axios.get<Order>(`/api/v1/orders/${id}`);
        return res.data;
    },
    async getOrCreateOrder(request: CreateOrderRequest): Promise<Order> {
        const res = await axios.post<Order>(`/api/v1/orders/get-or-create`, request);
        return res.data;
    },
}