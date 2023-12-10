import React from 'react';
import {Field, Form} from 'react-final-form';
import {InputText} from 'primereact/inputtext';
import {Button} from 'primereact/button';
import {Password} from 'primereact/password';
import {useAuth} from "../../auth";


export const LoginPage = () => {
    const {login} = useAuth();

    // @ts-ignore
    const onSubmit = (data, form: { restart: () => void; }) => {
        login(data.name, data.password);
        form.restart();
    };

    return (
        <div className="flex justify-content-center">
            <div className="card">
                <h5 className="text-center">Авторизация</h5>
                <Form onSubmit={onSubmit}
                      initialValues={{name: '', password: ''}} render={({handleSubmit}) => (
                    <form onSubmit={handleSubmit} className="p-fluid">
                        <Field name="name" render={({input}) => (
                            <div className="field">
                                    <span className="p-float-label">
                                        <InputText id="name" {...input} autoFocus/>
                                    </span>
                            </div>
                        )}/>
                        <Field name="password" render={({input}) => (
                            <div className="field">
                                    <span className="p-float-label">
                                        <Password id="password" {...input} toggleMask/>
                                    </span>
                            </div>
                        )}/>
                        <Button type="submit" label="Submit" className="mt-2"/>
                    </form>
                )}/>
            </div>
        </div>
    );
}