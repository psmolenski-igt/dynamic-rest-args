package com.example.dynamicrestarg;

import lombok.Data;

@Data
public class InstantGameTicketProduct implements Product {
    String name;
    Integer numberOfTickets;

    @Override
    public ProductType getType() {
        return ProductType.INSTANT_GAME_TICKET;
    }

    @Override
    public Integer getQuantity() {
        return getNumberOfTickets();
    }
}
