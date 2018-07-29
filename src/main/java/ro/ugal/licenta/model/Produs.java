package ro.ugal.licenta.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Entity
@Data
@Setter
@Getter
@Table(name = "Produs")
public class Produs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProdus;

    @Column(name = "nume", nullable = false, unique = true)
    @Length(min = 5, message = "*Numele trebuie sa aiba cel putin 5 caractere.")
    private String nume;

    @Column(name = "descriere")
    private String descriere;

    @Column(name = "cantitate", nullable = false)
    @Min(value = 0, message = "*Cantitatea trebuie sa fie un numar pozitiv.")
    private Integer cantitate;

    @Column(name = "pret", nullable = false)
    @DecimalMin(value = "0.00", message = "*Pretul trebuie sa fie un numar pozitiv.")
    private Double pret;

    @OneToOne
    private Categorie categorie;

    @Column()
    private String linkImagine;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produs produs = (Produs) o;

        return idProdus.equals(produs.idProdus);
    }

    @Override
    public int hashCode() {
        return idProdus.hashCode();
    }
}
