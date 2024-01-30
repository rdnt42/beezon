import {BarcodeParserService} from "../service/BarcodeParserService";
import {StorageManagerService} from "../service/StorageManagerService";
import {CreateOrderRequest} from "../obj/api/CreateOrderRequest";
import {BarcodeInfo} from "../obj/BarcodeInfo";

export interface ItemCardProps {
    qrValue: string
}
export const ItemCard = (props: ItemCardProps) => {
    let barcodeInfo: BarcodeInfo | undefined = undefined;
    if (props.qrValue && props.qrValue !== '') {
        barcodeInfo = BarcodeParserService.parseOrder(props.qrValue);
        const request: CreateOrderRequest = {
            orderNum: barcodeInfo.orderNum,
            client: barcodeInfo.client,
            itemsCount: barcodeInfo.partOf,
        };
        StorageManagerService.getOrCreateOrder(request).then(order => {
            console.log('got order', order);
        }).catch(error => {
            console.log(error)
        });
    }

    return (
        <>
            {barcodeInfo ? <label>Номер заказа: {barcodeInfo.orderNum}</label> : null }
        </>
    );
};