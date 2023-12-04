import React from 'react';
import ProcessingDeliveryTableComponent from "./component/ProcessingDeliveryTable";
import FinishedDeliveryTableComponent from "./component/FinishedDeliveryTable";

function App() {
  return (
    <div className="App">
      <header className="App-header">
          <ProcessingDeliveryTableComponent/>
          <FinishedDeliveryTableComponent/>
      </header>
    </div>
  );
}

export default App;
