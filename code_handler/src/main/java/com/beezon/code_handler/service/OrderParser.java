package com.beezon.code_handler.service;

import com.beezon.code_handler.dto.Order;

public class OrderParser {

    private OrderParser() {
    }

    public static Order parse(String text) {
        String[] strings = text.split("-");
        if (strings.length != 3) {
            throw new IllegalArgumentException("Неизвестный код");
        }

        var orderNum = strings[0];
        var client = strings[2].replace("_", " ");

        var parts = strings[1].split("_");
        var part = Integer.parseInt(parts[0]);
        var partOf = Integer.parseInt(parts[1]);

        return new Order(orderNum, part, partOf, client);
    }
}
