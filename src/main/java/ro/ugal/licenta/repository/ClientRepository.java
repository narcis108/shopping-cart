package ro.ugal.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ugal.licenta.model.Client;
import ro.ugal.licenta.model.Utilizator;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByNume(String nume);
    Client findByUtilizator(Utilizator utilizator);
}
