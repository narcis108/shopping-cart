package ro.ugal.licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ugal.licenta.model.Categorie;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Optional<Categorie> findByNume(String nume);
}
