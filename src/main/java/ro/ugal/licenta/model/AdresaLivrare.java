package ro.ugal.licenta.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
public class AdresaLivrare {
    @Id
    @GeneratedValue
    public Long idAdresaLivrare;

    private String adresa;
    private String oras;
    private String judet;
    private String tara;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAdresaLivrare")
    private Client idClient;
}
