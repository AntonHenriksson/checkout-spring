package se.checkoutspring.app.mapper;

import se.checkoutspring.app.dto.CartResponse;
import se.checkoutspring.app.model.Cart;

public class CartMapper {

    public static Cart createCartFromUserId(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        return cart;
    }

    public static CartResponse toCartResponse(Cart cart) {
        return new CartResponse(
                cart.getId(),
                cart.getUserId(),
                cart.getItems().stream().map(ItemMapper::toItemResponse).toList()
        );
    }
}
