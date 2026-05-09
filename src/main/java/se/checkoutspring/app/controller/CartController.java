package se.checkoutspring.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/{email}")
    public ResponseEntity<CartResponse> addToCart(Authentication authentication, @RequestBody ItemRequest request) {
        String email = authentication.getName();

        CartResponse updatedCart = cartService.addToCart(email, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCart);

    }

    @DeleteMapping("/{email}/items/{productId}")
    public ResponseEntity<Void> removeFromCart(Authentication authentication, @PathVariable Long productId) {
        String email = authentication.getName();

        cartService.removeFromCart(email, productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}/items")
    public ResponseEntity<CartResponse> getCart(Authentication authentication) {
        String email = authentication.getName();
        CartResponse updatedCart = cartService.getCart(email);

        return ResponseEntity.ok().body(updatedCart);
    }

    @PostMapping("/{email}/checkout")
    public ResponseEntity<String> checkout(Authentication authentication) {
        String email = authentication.getName();
        // om userId balance >= cartCost { http200 }
        return null;
    }
}
