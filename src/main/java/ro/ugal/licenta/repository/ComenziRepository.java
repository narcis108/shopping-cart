package ro.ugal.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ugal.licenta.model.Client;
import ro.ugal.licenta.model.ClientComenzi;
import ro.ugal.licenta.model.CosCumparaturi;

import java.util.List;

public interface ComenziRepository extends JpaRepository<ClientComenzi, Long> {
   List<ClientComenzi> findByClient(Client client);
   ClientComenzi findByIdComanda(Long idComanda);
}
