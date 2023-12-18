package com.beezon.code_handler;

import com.beezon.code_handler.dto.Order;
import com.beezon.code_handler.service.OrderParser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CommandController {
    @FXML
    public TextField inputBuffer;
    @FXML
    public Label orderInfoLbl;
    @FXML
    public Label readedBarCodeLbl;

    @FXML
    protected void onInputBufferText() {
        String text = inputBuffer.getText();
        inputBuffer.clear();

        System.out.println("Read: " + text);
        Order order = OrderParser.parse(text);

        orderInfoLbl.setText(order.info());
        readedBarCodeLbl.setText(text);
    }
}