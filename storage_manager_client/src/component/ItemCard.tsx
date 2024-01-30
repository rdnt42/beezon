import {BarcodeParserService} from "../service/BarcodeParserService";
import {StorageManagerService} from "../service/StorageManagerService";
import {CreateOrderRequest} from "../obj/api/CreateOrderRequest";
import {BarcodeInfo} from "../obj/BarcodeInfo";
import {useEffect, useState} from "react";
import {Order} from "../obj/Order";

export interface ItemCardProps {
    qrValue: string
}

export const ItemCard = (props: ItemCardProps) => {
    const [barcodeInfo, setBarcodeInfo] = useState<BarcodeInfo | undefined>(undefined);
    const [order, setOrder] = useState<Order | null>(null)

    useEffect(() => {
        if (props.qrValue && props.qrValue !== '') {
            let info = BarcodeParserService.parseOrder(props.qrValue);
            setBarcodeInfo(info);
            const {orderNum, client, part, partOf} = info;
            console.log(`decoded order num: ${orderNum}, client: ${client}, part ${part} from ${partOf}`);
            const request: CreateOrderRequest = {
                orderNum: orderNum,
                client: client,
                itemsCount: partOf,
            };
            StorageManagerService.getOrCreateOrder(request).then(order => {
                console.log('got order', order);
                setOrder(order);
            }).catch(error => {
                console.log(error)
            });
        }
    }, []);

    const OrderInfo = () => {
        if (order && barcodeInfo) {
            const {cell, id} = order;
            const cellInfo = `${cell ? `в яйчейке ${cell.placeCode}` : 'еще не добавлен'}`;
            return (
                <form>
                    <label>Заказ {id} {cellInfo}</label>
                    <label>Часть {barcodeInfo.part} из {barcodeInfo.partOf}</label>
                    <label>Клиент {order.client}</label>
                </form>
            )
        }

        return null;
    }

    return (
        <>
            {order && barcodeInfo ? <OrderInfo/> : null}
        </>
    );
};