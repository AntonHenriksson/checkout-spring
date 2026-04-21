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


    @PostMapping("/{email}")
    public ResponseEntity<CartResponse> addToCart(@PathVariable String email, @RequestBody ItemRequest request) {
        CartResponse updatedCart = cartService.addToCart(email, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCart);

    }

    @DeleteMapping("/{email}/items/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable String email, @PathVariable Long productId) {
        cartService.removeFromCart(email, productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}/items")
    public ResponseEntity<CartResponse> getCart(@PathVariable String email) {
        CartResponse updatedCart = cartService.getCart(email);

        return ResponseEntity.ok().body(updatedCart);
    }

    @PostMapping("/{email}/checkout")
    public ResponseEntity<String> checkout(@PathVariable String email) {
        // om userId balance >= cartCost { http200 }
        return null;
    }
}
