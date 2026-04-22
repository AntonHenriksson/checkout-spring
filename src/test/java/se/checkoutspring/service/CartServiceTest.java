package se.checkoutspring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.checkoutspring.app.dto.CartResponse;
import se.checkoutspring.app.dto.ItemRequest;
import se.checkoutspring.app.model.Cart;
import se.checkoutspring.app.repo.CartRepository;
import se.checkoutspring.app.service.CartService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    void addToCartTest() {

        //Arrange
        String email = "test@mail.se";
        ItemRequest request = new ItemRequest(1L, BigDecimal.valueOf(10));

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

        when(cartRepository.findByUserEmail(email)).thenReturn(Optional.of(expectedCart));

        //Act
        CartResponse result = cartService.getCart(email);

        //Assert
        assertNotNull(result);
        verify(cartRepository).findByUserEmail(email);
    }


}
