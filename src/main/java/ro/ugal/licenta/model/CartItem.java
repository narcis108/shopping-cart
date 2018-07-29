package ro.ugal.licenta.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue
    private Long cartItemId;

    private double pretTotal;

    @ManyToOne
    @JoinColumn(name = "idProdus")
    private Produs produs;

    private Integer cantitate;

    @ManyToOne
    @JoinColumn(name = "idCos")
    private CosCumparaturi cosCumparaturi;

    public CartItem(double pretTotal, Produs produs, CosCumparaturi cosCumparaturi, Integer cantitate) {
        this.pretTotal = pretTotal;
        this.produs = produs;
        this.cosCumparaturi = cosCumparaturi;
        this.cantitate = cantitate;
    }
}
