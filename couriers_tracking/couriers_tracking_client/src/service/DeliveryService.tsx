import axios from "axios";
import {Delivery} from "../obj/Delivery";

export const DeliveryService = {
    getProcessingDeliveries(): Promise<Delivery[]> {
        return axios.get<Delivery[]>(`/api/v1/deliveries?statuses=WAITING,IN_PROCESS`)
            .then(res => {
                return res.data;
            });
    },

    getFinishedDeliveries(): Promise<Delivery[]> {
        return axios.get(`/api/v1/deliveries?statuses=COMPLETED`)
            .then(res => {
                return res.data;
            });
    }
}