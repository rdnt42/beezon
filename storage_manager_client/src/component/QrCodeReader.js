import React, { useState } from 'react';
import { QrReader } from 'react-qr-reader';
export const QrParser = (props) => {
    const [data, setData] = useState(null);

    return (
        <>
            <QrReader
                onResult={(result, error) => {
                    if (!!result) {
                        setData(result?.text);
                    }

                    if (!!error) {
                        console.info(error);
                    }
                }}
                style={{ width: '100%' }}
             constraints={{ facingMode: 'environment' }}/>
            <p>{data}</p>
        </>
    );
};