package se.checkoutspring.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.checkoutspring.app.dto.ItemRequest;
import se.checkoutspring.app.dto.ItemResponse;
import se.checkoutspring.app.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ItemResponse> addToCart(@PathVariable Long userId, @RequestBody ItemRequest request) {
        return null;

        //servicemetod som adderar produkten till users cart
        //om users cart inte existerar i h2 skapa users cart
        //begär inte mer info än nödvändigt, inte carts roll att kräva

    }

    @DeleteMapping("/{userId}/items/{productId}")
    public ResponseEntity<ItemResponse> removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        //om users cart inte existerar returna 404
        //om item inte existerar returna 404
        //annars radera item ifrån cart
        return null;
    }

    @PostMapping("/{userId}/checkout")
    public ResponseEntity<String> checkout(@PathVariable String userId) {
        // om userId balance >= cartCost { http200 }
        return null;
    }
}
