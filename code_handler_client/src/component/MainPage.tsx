import {ChangeEvent, useEffect, useState} from "react";
import {InputText} from "primereact/inputtext";
import {useDebounce} from "use-debounce";
import {BarcodeParserService} from "../service/BarcodeParserService";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import {BarcodeInfo} from "../obj/BarcodeInfo";
import {StorageManagerService} from "../service/StorageManagerService";
import {CreateOrderRequest} from "../obj/api/CreateOrderRequest";
import {Order} from "../obj/Order";

export default function MainPage() {
    const [inputValue, setInputValue] = useState('');
    const [barcode] = useDebounce(inputValue, 100);
    const [barcodesStory] = useState(Array<BarcodeInfo>);
    const [selectedBarcode, setSelectedBarcode] = useState<BarcodeInfo | null>(null);
    const [searchOrder, setSearchOrder] = useState<Order | null>(null)

    useEffect(() => {
        if (barcode === '') return;
        console.log('Readed bar-code', barcode);

        const barcodeInfo = BarcodeParserService.parseOrder(barcode);
        console.log('Order info', barcodeInfo);

        setInputValue('');
        if (barcodesStory.find((order: BarcodeInfo) => order.barCode === barcode)) return;

        barcodesStory.push(barcodeInfo);
        onSelect({
            value: barcodeInfo
        })
    }, [barcode, barcodesStory]);

    const onChange = (e: ChangeEvent<HTMLInputElement>) => {
        setInputValue(e.target.value);
    }

    const onSelect = (e: any) => {
        let codeInfo: BarcodeInfo = e.value;
        setSelectedBarcode(codeInfo);
        if (codeInfo) {
            const request: CreateOrderRequest = {
                orderNum: codeInfo.orderNum,
                client: codeInfo.client,
                itemsCount: codeInfo.partOf,
            }
            StorageManagerService.getOrCreateOrder(request).then(order => {
                console.log('got order', order);
                setSearchOrder(order)
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
                <DataTable id="parsedBarcode" value={barcodesStory} showGridlines style={{width: 500}}
                           selectionMode="single" selection={selectedBarcode} onSelectionChange={onSelect}>
                    <Column field="orderNum" header="Номер заказа"></Column>
                    <Column field="part" header="Часть"></Column>
                    <Column field="partOf" header="Всего"></Column>
                    <Column field="client" header="Клиент"></Column>
                </DataTable>
            </div>
            <div className="flex flex-column gap-2">
                <label htmlFor="searchOrderNum">Поиск по номеру заказа</label>
                <InputText id="searchOrderNum" placeholder="Ввод номера заказа" value={selectedBarcode?.orderNum}
                           onChange={onChange}
                           style={{width: 500}}/>
                <label htmlFor="orderInfo">Информация о заказе</label>
                <DataTable id="orderInfo" value={searchOrder?.parts} showGridlines style={{width: 500}}>
                    <Column field="partNum" header="Часть заказа"></Column>
                    <Column field="barcode" header="Штрих-код"></Column>
                </DataTable>
            </div>
        </div>
    )
}