package ro.ugal.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ugal.licenta.model.CartItem;
import ro.ugal.licenta.model.CosCumparaturi;
import ro.ugal.licenta.model.Produs;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCosCumparaturi(CosCumparaturi cosCumparaturi);
}
