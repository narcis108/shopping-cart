package ro.ugal.licenta.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ClientComenzi {
    @Id
    @GeneratedValue
    private Long idComanda;

    @OneToOne
    @JoinColumn(name = "idCos")
    private CosCumparaturi cosCumparaturi;

    @OneToOne
    @JoinColumn(name = "idClient")
    private Client client;

    private Date dataComanda;

    private String status;


}
