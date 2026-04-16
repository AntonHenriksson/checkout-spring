package se.checkoutspring.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import se.checkoutspring.app.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
