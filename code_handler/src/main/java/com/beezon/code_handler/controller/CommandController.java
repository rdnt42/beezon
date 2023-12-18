package com.beezon.code_handler.controller;

import com.beezon.code_handler.dto.Order;
import com.beezon.code_handler.service.OrderParser;
import com.beezon.code_handler.service.QrGenerator;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

public class CommandController implements Initializable {
    @FXML
    public TextField inputBuffer;
    @FXML
    public Label orderInfoLbl;
    @FXML
    public Label readedBarCodeLbl;
    @FXML
    public ImageView soundOnImg;
    @FXML
    public ImageView soundOffImg;

    @FXML
    protected void onInputBufferText() {
        String text = inputBuffer.getText();
        inputBuffer.clear();

        System.out.println("Read: " + text);
        Order order = OrderParser.parse(text);

        orderInfoLbl.setText(order.info());
        readedBarCodeLbl.setText(text);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BufferedImage onImg;
        onImg = QrGenerator.generate("%%BZOpen1");
        this.soundOnImg.setImage(SwingFXUtils.toFXImage(onImg, null));

        BufferedImage offImp;
        offImp = QrGenerator.generate("%%BZClose1");
        this.soundOffImg.setImage(SwingFXUtils.toFXImage(offImp, null));
    }
}