package se.checkoutspring.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.checkoutspring.app.dto.CartResponse;
import se.checkoutspring.app.dto.ItemRequest;
import se.checkoutspring.app.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("/{userId}")
    public ResponseEntity<CartResponse> addToCart(@PathVariable Long userId, @RequestBody ItemRequest request) {
        CartResponse updatedCart = cartService.addToCart(userId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCart);

    }

    @DeleteMapping("/{userId}/items/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.removeFromCart(userId, productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/items")
    public ResponseEntity<CartResponse> getCart(@PathVariable Long userId) {
        CartResponse updatedCart = cartService.getCart(userId);

        return ResponseEntity.ok().body(updatedCart);
    }

    @PostMapping("/{userId}/checkout")
    public ResponseEntity<String> checkout(@PathVariable String userId) {
        // om userId balance >= cartCost { http200 }
        return null;
    }
}
