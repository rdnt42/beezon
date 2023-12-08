import React, {useState, useEffect} from 'react';
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import {DeliveryService} from '../service/DeliveryService';
import {Delivery} from "../obj/Delivery";


export default function FinishedDeliveryTable() {
    const [finishedDeliveries, setFinishedDeliveries] = useState<Delivery[]>([]);

    useEffect(() => {
        const fetchData = async () => {
            const data = await DeliveryService.getFinishedDeliveries()
            setFinishedDeliveries(data);
        };

        const intervalId = setInterval(() => {
            fetchData().then();
        }, 30000);

        return () => clearInterval(intervalId);
    }, []);

    const header = (
        <div className="flex flex-wrap align-items-center justify-content-between gap-2">
            <span className="text-xl text-900 font-bold">Завершены</span>
        </div>
    );

    return (
        <div className="card">
            <DataTable value={finishedDeliveries} header={header} tableStyle={{minWidth: '50rem'}}>
                <Column field="description" header="Статус"></Column>
                <Column field="performer" header="Курьер"></Column>
                <Column field="whoRequested" header="Заказчик"></Column>
                <Column field="orderNum" header="Номер заказа"></Column>
                <Column field="addressTo" header="Адрес доставки"></Column>
            </DataTable>
        </div>
    );
}