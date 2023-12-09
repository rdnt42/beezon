import React from 'react';
import DeliveryTable from "./component/DeliveryTable";
import {DeliveryService} from "./service/DeliveryService";

function App() {
  return (
    <div className="App">
      <header className="App-header">
          <DeliveryTable getDeliveries={DeliveryService.getWaitingDeliveries()} text={'На пути к пункту выдачи'}/>
          <DeliveryTable getDeliveries={DeliveryService.getProcessingDeliveries()} text={'Доставляются клиенту'}/>
          <DeliveryTable getDeliveries={DeliveryService.getFinishedDeliveries()} text={'Завершены'}/>
      </header>
    </div>
  );
}

export default App;
