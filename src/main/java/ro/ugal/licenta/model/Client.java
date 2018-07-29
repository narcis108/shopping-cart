package ro.ugal.licenta.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue
    private Long idClient;

    private String nume;
    private String prenume;
    private String telefon;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAdresaFacturare")
    private AdresaFacturare adresaFacturare;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAdresaLivrare")
    private AdresaLivrare adresaLivrare;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUtilizator")
    private Utilizator utilizator;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCos")
    private CosCumparaturi cosCumparaturi;

    public Client() {
    }

    public Client(String nume, String prenume, String telefon) {
        this.nume = nume;
        this.prenume = prenume;
        this.telefon = telefon;
    }
}
