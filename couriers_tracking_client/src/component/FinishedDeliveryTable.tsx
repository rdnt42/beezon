import React, {useState, useEffect} from 'react';
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import {DeliveryService} from '../service/DeliveryService';
import {Delivery} from "../obj/Delivery";


export default function FinishedDeliveryTable() {
    const [processingDeliveries, setProcessingDeliveries] = useState<Delivery[]>([]);

    useEffect(() => {
        DeliveryService.getFinishedDeliveries()
            .then(data => setProcessingDeliveries(data));
    }, []);

    const header = (
        <div className="flex flex-wrap align-items-center justify-content-between gap-2">
            <span className="text-xl text-900 font-bold">Завершенные заказы</span>
        </div>
    );

    return (
        <div className="card">
            <DataTable value={processingDeliveries} header={header} tableStyle={{minWidth: '50rem'}}>
                <Column field="description" header="Статус"></Column>
                <Column field="performer" header="Курьер"></Column>
                <Column field="whoRequested" header="Заказчик"></Column>
                <Column field="orderNum" header="Номер заказа"></Column>
                <Column field="addressTo" header="Адрес доставки"></Column>
            </DataTable>
        </div>
    );
}