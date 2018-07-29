package ro.ugal.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ugal.licenta.model.Categorie;
import ro.ugal.licenta.model.Produs;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Produs, Long> {
    Optional<Produs> findById(Long aLong);
    List<Produs> findByCategorie(Categorie categorie);
    List<Produs> findByNumeLike(String nume);
    List<Produs> findByCantitateGreaterThan(Integer cantitateMinima);
    List<Produs> findAllByOrderByIdProdusDesc();
}
