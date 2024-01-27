import React, {useEffect, useState} from 'react';
import {QrParser} from "./component/QrCodeReader";
import 'primereact/resources/themes/lara-light-indigo/theme.css';   // theme
import 'primeflex/primeflex.css';
import {Button} from "primereact/button";
import "primereact/resources/themes/lara-light-indigo/theme.css";
import "./css/component.css"
import {ItemCard} from "./component/ItemCard";

function App() {
    const [scanEnabled, setScanEnabled] = useState(false);
    const [qrValue, setQrValue] = useState('');
    const buttonText = `${scanEnabled ? 'Выключить сканнер' : 'Включить сканнер'} `;

    const qrCodeCallback = (qrValue) => {
        console.log('qr callback', qrValue);
        setScanEnabled(false);
        setQrValue(qrValue);
    }

    const onScanBtnClick = () => {
        setScanEnabled(!scanEnabled);
    }

    return (
        <>
            {scanEnabled ? <QrParser qrHandle={qrCodeCallback}/> : <ItemCard qrValue={qrValue}/>}
            <Button className="ScanButton" label={buttonText} onClick={onScanBtnClick}/>
        </>
    );
}

export default App;
