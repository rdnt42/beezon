package com.beezon.code_handler.dto;

public record Order(
        String orderNum,
        int part,
        int partOf,
        String client
) {
    public String info() {
        StringBuilder sb = new StringBuilder();
        sb.append("Заказ номер ");
        sb.append("\"").append(orderNum).append("\".");
        sb.append("Часть ").append(part).append(" из ").append(partOf);
        sb.append("\n").append("Клиент ").append(client);

        return sb.toString();
    }
}
