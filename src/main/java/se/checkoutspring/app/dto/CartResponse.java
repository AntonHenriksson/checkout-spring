package se.checkoutspring.app.dto;

import java.util.List;

public record CartResponse(
        Long id,
        Long userId,
        List<ItemResponse> items
) {
}
