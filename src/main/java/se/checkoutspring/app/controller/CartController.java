package se.checkoutspring.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import se.checkoutspring.app.dto.ItemRequest;
import se.checkoutspring.app.dto.ItemResponse;
import se.checkoutspring.app.model.Cart;
import se.checkoutspring.app.service.CartService;

import java.math.BigDecimal;

@Controller
@RequestMapping("/checkout")
public class CartController {

    CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<ItemResponse> addToCart(@RequestBody ItemRequest itemRequest) {

    }
}
