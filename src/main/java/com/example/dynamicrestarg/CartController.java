package com.example.dynamicrestarg;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/cart")
public class CartController {
    final ObjectMapper objectMapper;

    public CartController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/add-product")
    public Product addProduct(@RequestBody Map<String, Object> argsMap) {
        ProductType productType = findProductType(argsMap)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product type"));

        switch (productType) {
            case DRAW_GAME_WAGER -> {
                DrawGameWagerProduct drawGameWagerProduct = objectMapper.convertValue(argsMap, DrawGameWagerProduct.class);

                return addWager(drawGameWagerProduct);
            }
            case INSTANT_GAME_TICKET -> {
                InstantGameTicketProduct instantGameTicketProduct = objectMapper.convertValue(argsMap, InstantGameTicketProduct.class);

                return addInstant(instantGameTicketProduct);
            }
        }

        return null;
    }

    private Product addInstant(InstantGameTicketProduct instantGameTicketProduct) {
        log.info("Adding new instant ticket {}", instantGameTicketProduct);

        return instantGameTicketProduct;
    }

    private Product addWager(DrawGameWagerProduct drawGameWagerProduct) {

        log.info("Adding new wager {}", drawGameWagerProduct);

        return drawGameWagerProduct;
    }

    private Optional<ProductType> findProductType(Map<String, Object> argsMap) {
        try {
            ProductType productType = objectMapper.convertValue(argsMap.get("type"), ProductType.class);

            return Optional.of(productType);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
