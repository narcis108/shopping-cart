package ro.ugal.licenta.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Setter
@Getter
public class CosCumparaturi{

    @Id @GeneratedValue
    private Long idCos;

    @OneToOne
    @JoinColumn(name = "idClient")
    private Client client;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> cartItem;

    private double price;
}
