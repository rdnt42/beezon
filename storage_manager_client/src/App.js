import React, {useState} from 'react';
import {QrParser} from "./component/QrCodeReader";
import 'primereact/resources/themes/lara-light-indigo/theme.css';   // theme
import 'primeflex/primeflex.css';
import {Button} from "primereact/button";
import "primereact/resources/themes/lara-light-indigo/theme.css";
import "./css/component.css"

function App() {
    const [scanEnabled, setScanEnabled] = useState(false);
    const buttonText = `${scanEnabled ? 'Выключить сканнер' : 'Включить сканнер'} `;
    const onScanClick = () => {
        setScanEnabled(!scanEnabled);

        console.log(scanEnabled);
    };
    return scanEnabled ? (
        <>
            <QrParser/>
            <Button className="ScanButton" label={buttonText}
                    severity="danger" onClick={() => setScanEnabled(!scanEnabled)}/>
        </>
    ) : (
        <>
            <div className="grid">
                <div className="col">
                    <Button className="ScanButton"
                            label={buttonText} onClick={() => setScanEnabled(!scanEnabled)}/>
                </div>
            </div>
        </>
    );
}

export default App;