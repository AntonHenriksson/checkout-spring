package se.checkoutspring.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.checkoutspring.app.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
