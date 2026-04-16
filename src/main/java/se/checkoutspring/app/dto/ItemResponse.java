package se.checkoutspring.app.dto;

import java.math.BigDecimal;

public record ItemResponse(

        Long id,
        Long productId,
        BigDecimal price

) {
}
