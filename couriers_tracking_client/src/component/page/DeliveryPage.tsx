import React from "react";
import DeliveryTable from "../DeliveryTable";
import {DeliveryService} from "../../service/DeliveryService";
import CreateNewDelivery from "../CreateNewDelivery";
import {Navigate} from "react-router-dom";
import {useAuth} from "../../auth";

export const DeliveryPage = () => {
    const {cookies, isValidToken} = useAuth();

    return isValidToken() ? (
        <div>
            <CreateNewDelivery/>
            <DeliveryTable getDeliveries={() => DeliveryService.getWaitingDeliveries(cookies.token)}
                           text={'На пути к пункту выдачи'}/>
            <DeliveryTable getDeliveries={() => DeliveryService.getProcessingDeliveries(cookies.token)}
                           text={'Доставляются клиенту'}/>
            <DeliveryTable getDeliveries={() => DeliveryService.getFinishedDeliveries(cookies.token)}
                           text={'Завершены'}/>
        </div>
    ) : (
        <Navigate to={{pathname: '/login'}}/>
    );
}