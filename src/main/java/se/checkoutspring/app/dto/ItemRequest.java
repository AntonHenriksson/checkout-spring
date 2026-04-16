package se.checkoutspring.app.dto;

import java.math.BigDecimal;

public record ItemRequest(
        Long productId,
        BigDecimal price
) {
}
