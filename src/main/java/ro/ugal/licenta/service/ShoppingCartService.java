package ro.ugal.licenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.thymeleaf.util.MapUtils;
import ro.ugal.licenta.model.Client;
import ro.ugal.licenta.model.CosCumparaturi;
import ro.ugal.licenta.model.Produs;
import ro.ugal.licenta.repository.CartRepository;
import ro.ugal.licenta.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Shopping Cart is implemented with a Map, and as a session bean
 *
 * @author Dusan
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    private Map<Produs, Integer> products = new HashMap<>();

    public ShoppingCartService(ProductRepository productRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    /**
     * If produs is in the map just increment quantity by 1.
     * If produs is not in the map with, add it with quantity 1
     *
     * @param produs
     */
    public void addProduct(Produs produs) {
        if (products.containsKey(produs)) {
            products.replace(produs, products.get(produs) + 1);
        } else {
            products.put(produs, 1);
        }
    }

    /**
     * If produs is in the map with quantity > 1, just decrement quantity by 1.
     * If produs is in the map with quantity 1, remove it from map
     *
     * @param produs
     */
    public void removeProduct(Produs produs) {
        if (products.containsKey(produs)) {
            if (products.get(produs) > 1)
                products.replace(produs, products.get(produs) - 1);
            else if (products.get(produs) == 1) {
                products.remove(produs);
            }
        }
    }

    /**
     * @return unmodifiable copy of the map
     */
    public Map<Produs, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    public CosCumparaturi adaugaCosCumparaturi(Client client) {
        CosCumparaturi cosCumparaturi = new CosCumparaturi();
        cosCumparaturi.setClient(client);
        return cartRepository.save(cosCumparaturi);
    }

    public void clearProductsInCart() {
        products = new HashMap<>();
    }

//    public BigDecimal getTotal() {
//        return products.entrySet().stream()
//                .map(entry -> entry.getKey().getPret().multiply(BigDecimal.valueOf(entry.getValue())))
//                .reduce(BigDecimal::add)
//                .orElse(BigDecimal.ZERO);
//    }
}
