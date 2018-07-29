package ro.ugal.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.ugal.licenta.model.Client;
import ro.ugal.licenta.model.Utilizator;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Utilizator, Long> {
    Optional<Utilizator> findByEmail(String email);

    Utilizator findByConfirmationToken(String confirmationToken);
}
