import {ChangeEvent, useEffect, useState} from "react";
import {InputText} from "primereact/inputtext";
import {useDebounce} from "use-debounce";

export default function MainPage() {
    const [inputValue, setInputValue] = useState('');
    const [barCode] = useDebounce(inputValue, 100);

    useEffect(() => {
        if (barCode === '') return;
        console.log('Readed bar-code', barCode);
        setInputValue('');
    }, [barCode]);

    const onChange = (e: ChangeEvent<HTMLInputElement>) => {
        setInputValue(e.target.value);
    }

    return (
        <div className="card flex">
            <div className="flex flex-column gap-2">
                <label htmlFor="codeBuffer">Считанный штрих-код</label>
                <InputText id="codeBuffer" placeholder="Ввод штрих-кода" autoFocus={true} value={inputValue}
                           onChange={onChange}
                           style={{width: 500}}/>
            </div>
        </div>
    )
}