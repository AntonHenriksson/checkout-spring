package se.checkoutspring.app.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.checkoutspring.app.dto.CartResponse;
import se.checkoutspring.app.dto.ItemRequest;
import se.checkoutspring.app.dto.ItemResponse;
import se.checkoutspring.app.dto.ProductResponse;
import se.checkoutspring.app.mapper.CartMapper;
import se.checkoutspring.app.mapper.ItemMapper;
import se.checkoutspring.app.model.Cart;
import se.checkoutspring.app.model.Item;
import se.checkoutspring.app.repo.CartRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    @Transactional
    public CartResponse addToCart(String userEmail, ItemRequest request) {

        Cart cart = cartRepository.findByUserEmail(userEmail)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserEmail(userEmail);
                    return cartRepository.save(newCart);
                });

        Item newItem = ItemMapper.toItem(request);
        cart.getItems().add(newItem);

        Cart savedCart = cartRepository.save(cart);

        return CartMapper.toCartResponse(savedCart);
    }


    @Transactional
    public void removeFromCart(String userEmail, Long productId) {

        Cart cart = cartRepository.findByUserEmail(userEmail)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserEmail(userEmail);
                    return cartRepository.save(newCart);
                });

        Item itemToRemove = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Item with id " + productId + " not found"));

        cart.getItems().remove(itemToRemove);
        cartRepository.save(cart);
    }

    @Transactional
    public CartResponse getCart(String userEmail) {

        Cart cart = cartRepository.findByUserEmail(userEmail)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserEmail(userEmail);
                    return cartRepository.save(newCart);
                });

        List<Long> productIds = cart.getItems().stream()
                .map(Item::getProductId)
                .toList();
        List<ProductResponse> productDetails = productService.fetchRealData(productIds);

        Map<Long, ProductResponse> detailsMap = productDetails.stream()
                .collect(Collectors.toMap(ProductResponse::id, d -> d));

        List<ItemResponse> itemResponses = cart.getItems().stream()
                .map(item -> {
                    ProductResponse details = detailsMap.get(item.getProductId());
                    return (details != null)
                            ? ItemMapper.toItemResponse(item, details)
                            : ItemMapper.fromItemToResponse(item);
                })
                .toList();

        return new CartResponse(cart.getId(), cart.getUserEmail(), itemResponses);
    }


}
