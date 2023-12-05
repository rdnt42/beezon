import React from 'react';
import ProcessingDeliveryTable from "./component/ProcessingDeliveryTable";
import FinishedDeliveryTable from "./component/FinishedDeliveryTable";
import CreateNewDelivery from "./component/CreateNewDelivery";
import WaitingDeliveryTable from "./component/WaitingDeliveryTable";

function App() {
  return (
    <div className="App">
      <header className="App-header">
          <CreateNewDelivery/>
          <WaitingDeliveryTable/>
          <ProcessingDeliveryTable/>
          <FinishedDeliveryTable/>
      </header>
    </div>
  );
}

export default App;
