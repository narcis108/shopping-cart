package ro.ugal.licenta.exception;


import ro.ugal.licenta.model.Produs;

public class NotEnoughProductsInStockException extends Exception {

    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public NotEnoughProductsInStockException() {
        super(DEFAULT_MESSAGE);
    }

    public NotEnoughProductsInStockException(Produs produs) {
        super(String.format("Not enough %s products in stock. Only %d left", produs.getNume(), produs.getCantitate()));
    }

}
