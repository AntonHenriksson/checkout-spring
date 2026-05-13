package se.checkoutspring.app.mapper;

import se.checkoutspring.app.dto.CartResponse;
import se.checkoutspring.app.model.Cart;

public class CartMapper {

    public static CartResponse toCartResponse(Cart cart) {
        return new CartResponse(
                cart.getId(),
                cart.getUserEmail(),
                cart.getItems().stream().map(ItemMapper::fromItemToResponse).toList()
        );
    }
}
