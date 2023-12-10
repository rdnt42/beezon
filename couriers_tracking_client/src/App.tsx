import React from 'react';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import {LoginPage} from "./component/page/LoginPage";
import {DeliveryPage} from "./component/page/DeliveryPage";
import {AppProvider} from "./AppProvider";


function App() {
    return (
        <Router>
            <AppProvider>
                <div>
                    <Routes>
                        <Route path="/" element={<DeliveryPage/>}/>
                        <Route path="/login" element={<LoginPage/>}/>
                    </Routes>
                </div>
            </AppProvider>
        </Router>
    );
}

export default App;
