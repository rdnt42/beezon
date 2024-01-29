import React, { useState } from 'react';
import { QrReader } from 'react-qr-reader';
export const QrParser = (props) => {
    const qrReader = React.createRef();
    const handleScan = (result, error) => {
        if (!!result) {
            props.qrHandle(result?.text);
            qrReader.current.stop();
        }

        if (!!error) {
            console.info(error);
        }
    };
    return (
        <>
            <QrReader
                onResult={(result, error) => {
                    handleScan(result, error);
                }}
                style={{ width: '100%' }}
             constraints={{ facingMode: 'environment' }}/>
        </>
    );
};