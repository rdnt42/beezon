import React, {ChangeEvent, useState} from 'react';
import {InputText} from 'primereact/inputtext';
import {Button} from "primereact/button";
import {DeliveryService} from '../service/DeliveryService';

export default function CreateNewDelivery() {
    const [url, setUrl] = useState<string>('');
    const [orderNum, setOrderNum] = useState<string | undefined>(undefined);

    const createDelivery = () => {
        DeliveryService.createDelivery({
            url,
            orderNum
        }).then(r => console.log('created new order'))
    }

    // TODO
    const handleUrl = (e: ChangeEvent<HTMLInputElement>) => {
        setUrl(e.target.value);
    };
    const handleOrderNum = (e: ChangeEvent<HTMLInputElement>) => {
        setOrderNum(e.target.value);
    };

    return (
        <div className="card flex flex-column md:flex-row gap-3">
            <div className="p-inputgroup flex-1">
                <span className="p-inputgroup-addon">
                    <i className="pi pi-truck"></i>
                </span>
                <InputText placeholder="Ссылка на заказ" value={url} onChange={handleUrl}/>
            </div>

            <div className="p-inputgroup flex-1">
                <span className="p-inputgroup-addon">
                    <i className="pi pi-id-card"></i>
                </span>
                <InputText placeholder="Номер заказа" value={orderNum} onChange={handleOrderNum}/>
            </div>

            <Button label="Добавить доставку" onClick={createDelivery}/>
        </div>
    )
}