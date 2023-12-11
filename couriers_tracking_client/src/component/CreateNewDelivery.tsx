import React, {ChangeEvent, useRef, useState} from 'react';
import {InputText} from 'primereact/inputtext';
import {Button} from "primereact/button";
import {DeliveryService} from '../service/DeliveryService';
import {Toast} from "primereact/toast";
import {useAuth} from "../auth";

export default function CreateNewDelivery() {
    const {cookies} = useAuth();

    const toast = useRef<Toast>(null);
    const [url, setUrl] = useState<string>('');
    const [orderNum, setOrderNum] = useState<string | undefined>(undefined);

    const createDelivery = () => {
        DeliveryService.createDelivery({
            url,
            orderNum
        }, cookies.token).then(r => {
            // @ts-ignore
            toast.current.show({severity: 'success', summary: 'Success', detail: `Добавлен новый заказ ${r.orderNum}`});
            setUrl('')
            setOrderNum(undefined)
            window.location.reload();
        }).catch(error => {
            console.log(error)
            // @ts-ignore
            toast.current.show({
                severity: 'error', summary: 'Error', detail: 'Ошибка при добавлении зазказа'
            })
        });
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
            <Toast ref={toast}></Toast>
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

            <Toast ref={toast}></Toast>
            <Button label="Добавить заказ" onClick={createDelivery}/>
        </div>
    )
}