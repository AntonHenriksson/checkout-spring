package se.checkoutspring.app.dto;

import java.util.List;

public record CartResponse(
        Long id,
        String userEmail,
        List<ItemResponse> items
) {
}
