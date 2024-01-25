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
        setQrValue(qrValue);
        // setScanEnabled(false);
    }

    const onScanBtnClick = () => {
        setScanEnabled(!scanEnabled);
    }

    useEffect(() => {
            setScanEnabled(false);
        // if (scanEnabled) {
        //     setQrValue('');
        // }
    }, [qrValue]);

    // useEffect(() => {
    //     if (scanEnabled) {
    //         setQrValue('');
    //     }
    // }, [scanEnabled]);

    return (
        <>
            {scanEnabled ? <QrParser qrHandle={qrCodeCallback}/> : null}
            <Button className="ScanButton" label={buttonText} onClick={onScanBtnClick}/>
        </>
    );
}

export default App;
