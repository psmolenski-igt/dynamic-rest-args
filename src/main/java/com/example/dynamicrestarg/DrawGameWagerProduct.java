package com.example.dynamicrestarg;

import lombok.Data;

@Data
public class DrawGameWagerProduct implements Product {
    String name;
    Integer multiplay;

    @Override
    public ProductType getType() {
        return ProductType.DRAW_GAME_WAGER;
    }

    @Override
    public Integer getQuantity() {
        return getMultiplay();
    }
}
