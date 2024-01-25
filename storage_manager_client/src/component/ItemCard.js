import React from 'react';
import {BarcodeParserService} from "../service/BarcodeParserService";

export const ItemCard = (props) => {
    let barcodeInfo = BarcodeParserService.parseOrder(props.qrValue);

    return (
        <>
            <label>Номер заказа: {barcodeInfo.orderNum}</label>
        </>
    );
};