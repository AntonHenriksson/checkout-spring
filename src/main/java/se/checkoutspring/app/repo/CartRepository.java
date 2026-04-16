package se.checkoutspring.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.checkoutspring.app.model.Cart;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {


    Optional<Cart> findByUserId(Long userId);
}
