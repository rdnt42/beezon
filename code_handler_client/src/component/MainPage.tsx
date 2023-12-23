import {ChangeEvent, useEffect, useState} from "react";
import {InputText} from "primereact/inputtext";
import {useDebounce} from "use-debounce";
import {BarcodeParserService} from "../service/BarcodeParserService";
import {DataTable, DataTableSelectionSingleChangeEvent} from "primereact/datatable";
import {Column} from "primereact/column";
import {OrderInfo} from "../obj/OrderInfo";

export default function MainPage() {
    const [inputValue, setInputValue] = useState('');
    const [barCode] = useDebounce(inputValue, 100);
    const [orders] = useState(Array<OrderInfo>);
    const [selectedOrder, setSelectedOrder] = useState<OrderInfo | null>(null);

    useEffect(() => {
        if (barCode === '') return;
        console.log('Readed bar-code', barCode);

        const orderInfo = BarcodeParserService.parseOrder(barCode);
        console.log('Order info', orderInfo);

        setInputValue('');
        if(orders.find((order: OrderInfo) => order.barCode === barCode)) return;

        orders.push(orderInfo);
    }, [barCode, orders]);

    const onChange = (e: ChangeEvent<HTMLInputElement>) => {
        setInputValue(e.target.value);
    }

    const onSelect = (e: any) => {
        setSelectedOrder(e.value);
        console.log('Select value', e.value);

    }

    return (
        <div className="card flex">
            <div className="flex flex-column gap-2">
                <label htmlFor="searchBarCode">Поиск по штрих коду</label>
                <InputText id="searchBarCode" placeholder="Ввод штрих-кода" autoFocus={true} value={inputValue}
                           onChange={onChange}
                           style={{width: 500}}/>
                <DataTable value={orders} showGridlines style={{width: 500}}
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