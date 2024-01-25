import React, { useState } from 'react';
import { QrReader } from 'react-qr-reader';
export const QrParser = (props) => {
    return (
        <>
            <QrReader
                onResult={(result, error) => {
                    if (!!result) {
                        props.qrHandle(result?.text);
                    }

                    if (!!error) {
                        console.info(error);
                    }
                }}
                style={{ width: '100%' }}
             constraints={{ facingMode: 'environment' }}/>
        </>
    );
};