package ro.ugal.licenta.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
public class Categorie {
    @Id
    @GeneratedValue
    private Long idCategorie;

    private String nume;

}
