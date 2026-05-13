package se.checkoutspring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.checkoutspring.app.dto.CartResponse;
import se.checkoutspring.app.dto.ItemRequest;
import se.checkoutspring.app.dto.ProductResponse;
import se.checkoutspring.app.model.Cart;
import se.checkoutspring.app.model.Item;
import se.checkoutspring.app.repo.CartRepository;
import se.checkoutspring.app.service.CartService;
import se.checkoutspring.app.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private CartService cartService;


    @Test
    void addToCartTest() {

        //Arrange
        String email = "test@mail.se";
        ItemRequest request = new ItemRequest(1L);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUserEmail(email);
        cart.setItems(new ArrayList<>());

        when(cartRepository.findByUserEmail(email)).thenReturn(Optional.of(cart));
        when(cartRepository.save(any(Cart.class))).thenAnswer(i -> i.getArguments()[0]);

        //Act
        cartService.addToCart(email, request);

        //Assert
        verify(cartRepository).save(any(Cart.class));
        assertEquals(1, cart.getItems().size());
    }

    @Test
    void getCartTest() {

        //Arrange
        String email = "test@mail.se";
        Cart expectedCart = new Cart();
        expectedCart.setUserEmail(email);
        expectedCart.setId(1L);

        Item item = new Item();
        item.setProductId(101L);
        expectedCart.setItems(new ArrayList<>(List.of(item)));

        ProductResponse mockProduct = new ProductResponse(
                101L, 11L, BigDecimal.valueOf(99.99), "Title", "Description", "clothes", "image.jpg"
        );

        when(cartRepository.findByUserEmail(email)).thenReturn(Optional.of(expectedCart));
        when(productService.fetchRealData(anyList())).thenReturn(List.of(mockProduct));


        //Act
        CartResponse result = cartService.getCart(email);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.userEmail());
        assertEquals(1, result.items().size());


        assertEquals("Title", result.items().get(0).title());
        assertEquals(BigDecimal.valueOf(99.99), result.items().get(0).price());

        verify(cartRepository).findByUserEmail(email);
        verify(productService).fetchRealData(anyList());
    }


}
