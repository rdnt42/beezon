import React from 'react';
import {BarcodeParserService} from "../service/BarcodeParserService";
import {StorageManagerService} from "../service/StorageManagerService";

export const ItemCard = (props) => {
    let barcodeInfo = BarcodeParserService.parseOrder(props.qrValue);
    const request = {
        orderNum: barcodeInfo.orderNum,
        client: barcodeInfo.client,
        itemsCount: barcodeInfo.partOf,
    }
    StorageManagerService.getOrCreateOrder(request).then(order => {
        console.log('got order', order);
    }).catch(error => {
        console.log(error)
    });

    return (
        <>
            <label>Номер заказа: {barcodeInfo.orderNum}</label>
        </>
    );
};