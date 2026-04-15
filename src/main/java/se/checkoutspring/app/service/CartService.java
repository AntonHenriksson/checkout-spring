package se.checkoutspring.app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import se.checkoutspring.app.dto.ItemRequest;

@Service
public class CartService {





    public boolean addToCart(@RequestBody ItemRequest request){



        return true;
    }








}
