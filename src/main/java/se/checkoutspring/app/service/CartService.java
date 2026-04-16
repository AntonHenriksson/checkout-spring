package se.checkoutspring.app.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.checkoutspring.app.dto.CartResponse;
import se.checkoutspring.app.dto.ItemRequest;
import se.checkoutspring.app.mapper.CartMapper;
import se.checkoutspring.app.mapper.ItemMapper;
import se.checkoutspring.app.model.Cart;
import se.checkoutspring.app.model.Item;
import se.checkoutspring.app.repo.CartRepository;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    @Transactional
    public CartResponse addToCart(Long userId, ItemRequest request) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    return cartRepository.save(newCart);
                });

        Item newItem = ItemMapper.toItem(request);
        cart.getItems().add(newItem);

        Cart savedCart = cartRepository.save(cart);

        return CartMapper.toCartResponse(savedCart);
    }

    @Transactional
    public void removeFromCart(Long userId, Long productId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    return cartRepository.save(newCart);
                });


        Item itemToRemove = cart.getItems().stream()
                .filter(item -> item.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Item with id " + productId + " not found"));

        cart.getItems().remove(itemToRemove);
        cartRepository.save(cart);
    }

    @Transactional
    public CartResponse getCart(Long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    return cartRepository.save(newCart);
                });

        return CartMapper.toCartResponse(cart);

    }


}
