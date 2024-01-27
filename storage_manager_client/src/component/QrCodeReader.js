import React from 'react';
import QrReader from 'react-qr-reader'

export const QrParser = (props) => {
    const handleScan = (result) => {
        if (result) {
            props.qrHandle(result);
        }
    };

    const handleError = (error) => {
        console.error(error)
    };
    return (
        <>
            <QrReader
                onScan={handleScan}
                onError={handleError}
                style={{width: '100%'}}
                constraints={{facingMode: 'environment'}}/>
        </>
    );
};