package ro.ugal.licenta.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AdresaFacturare {
    @Id @GeneratedValue
    public Long idAdresaFacturare;

    private String adresa;
    private String oras;
    private String judet;
    private String tara;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAdresaFacturare")
    private Client idClient;
}
