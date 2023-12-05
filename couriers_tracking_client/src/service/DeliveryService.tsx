import axios from "axios";
import {Delivery} from "../obj/Delivery";
import {CreateDeliveryRequest} from "../obj/CreateDeliveryRequest";

export const DeliveryService = {
    getWaitingDeliveries(): Promise<Delivery[]> {
        return axios.get<Delivery[]>(`/api/v1/deliveries?statuses=WAITING`)
            .then(res => {
                return res.data;
            });
    },
    getProcessingDeliveries(): Promise<Delivery[]> {
        return axios.get<Delivery[]>(`/api/v1/deliveries?statuses=IN_PROCESS`)
            .then(res => {
                return res.data;
            });
    },

    getFinishedDeliveries(): Promise<Delivery[]> {
        return axios.get(`/api/v1/deliveries?statuses=COMPLETED`)
            .then(res => {
                return res.data;
            });
    },

    createDelivery(request: CreateDeliveryRequest): Promise<Delivery[]> {
        return axios.post(`/api/v1/deliveries`, request)
            .then(res => {
                return res.data;
            });
    }
}