import axios from "axios";
import {Delivery} from "../obj/Delivery";
import {CreateDeliveryRequest} from "../obj/CreateDeliveryRequest";

export const DeliveryService = {
    getWaitingDeliveries(token: string): Promise<Delivery[]> {
        return axios.get<Delivery[]>(`/api/v1/deliveries?statuses=WAITING`, this.getHeaders(token))
            .then(res => {
                return res.data;
            });
    },
    getProcessingDeliveries(token: string): Promise<Delivery[]> {
        return axios.get<Delivery[]>(`/api/v1/deliveries?statuses=IN_PROCESS`, this.getHeaders(token))
            .then(res => {
                return res.data;
            });
    },

    getFinishedDeliveries(token: string): Promise<Delivery[]> {
        return axios.get(`/api/v1/deliveries?statuses=COMPLETED`, this.getHeaders(token))
            .then(res => {
                return res.data;
            });
    },

    createDelivery(request: CreateDeliveryRequest, token: string): Promise<Delivery[]> {
        return axios.post(`/api/v1/deliveries`, request, this.getHeaders(token))
            .then(res => {
                return res.data;
            });
    },

    getHeaders(token: string) {
        return {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };
    },
}