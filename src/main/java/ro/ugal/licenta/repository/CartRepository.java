package ro.ugal.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ugal.licenta.model.CosCumparaturi;

public interface CartRepository extends JpaRepository<CosCumparaturi, Long> {
    
}
