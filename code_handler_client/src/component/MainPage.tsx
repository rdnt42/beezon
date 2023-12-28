import {ChangeEvent, useEffect, useState} from "react";
import {InputText} from "primereact/inputtext";
import {useDebounce} from "use-debounce";
import {BarcodeParserService} from "../service/BarcodeParserService";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import {BarcodeInfo} from "../obj/BarcodeInfo";
import {StorageManagerService} from "../service/StorageManagerService";

export default function MainPage() {
    const [inputValue, setInputValue] = useState('');
    const [barcode] = useDebounce(inputValue, 100);
    const [orders] = useState(Array<BarcodeInfo>);
    const [selectedOrder, setSelectedOrder] = useState<BarcodeInfo | null>(null);

    useEffect(() => {
        if (barcode === '') return;
        console.log('Readed bar-code', barcode);

        const barcodeInfo = BarcodeParserService.parseOrder(barcode);
        console.log('Order info', barcodeInfo);

        setInputValue('');
        if(orders.find((order: BarcodeInfo) => order.barCode === barcode)) return;

        orders.push(barcodeInfo);
    }, [barcode, orders]);

    const onChange = (e: ChangeEvent<HTMLInputElement>) => {
        setInputValue(e.target.value);
    }

    const onSelect = (e: any) => {
        let order = e.value;
        setSelectedOrder(order);
        if (order) {
            StorageManagerService.getOrder(order.orderNum).then(order => {
                console.log('got order', order);
            }).catch(error => {
                console.log(error)
                // @ts-ignore

            });
        }
        console.log('Select value', e.value);

    }

    return (
        <div className="card flex">
            <div className="flex flex-column gap-2">
                <label htmlFor="searchBarcode">Поиск по штрих-коду</label>
                <InputText id="searchBarcode" placeholder="Ввод штрих-кода" autoFocus={true} value={inputValue}
                           onChange={onChange}
                           style={{width: 500}}/>
                <label htmlFor="parsedBarcode">Расшифрованный штрих-код</label>
                <DataTable id="parsedBarcode" value={orders} showGridlines style={{width: 500}}
                           selectionMode="single" selection={selectedOrder} onSelectionChange={onSelect}>
                    <Column field="orderNum" header="Номер заказа"></Column>
                    <Column field="part" header="Часть"></Column>
                    <Column field="partOf" header="Всего"></Column>
                    <Column field="client" header="Клиент"></Column>
                </DataTable>
            </div>
            <div className="flex flex-column gap-2">
                <label htmlFor="searchOrderNum">Поиск по номеру заказа</label>
                <InputText id="searchOrderNum" placeholder="Ввод номера заказа" value={selectedOrder?.orderNum}
                           onChange={onChange}
                           style={{width: 500}}/>
            </div>
        </div>
    )
}