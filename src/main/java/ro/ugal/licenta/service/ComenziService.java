package ro.ugal.licenta.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ro.ugal.licenta.model.*;
import ro.ugal.licenta.repository.CartItemRepository;
import ro.ugal.licenta.repository.ComenziRepository;

import java.util.*;

@Service
public class ComenziService {

    ComenziRepository comenziRepository;
    ShoppingCartService shoppingCartService;
    UserService userService;
    ClientService clientService;
    ProductService productService;
    CartItemRepository cartItemRepository;

    public ComenziService(ComenziRepository comenziRepository,
                          ShoppingCartService shoppingCartService, UserService userService,
                          ClientService clientService, ProductService productService,
                          CartItemRepository cartItemRepository) {
        this.comenziRepository = comenziRepository;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.clientService = clientService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    public void adaugaComanda() {
        final ClientComenzi comanda = new ClientComenzi();
        Double pretTotalCosCumparaturi = Double.valueOf(0);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Utilizator> utilizator = userService.findByEmail(user.getUsername());
        Client client = clientService.findByUtilizator(utilizator.get());
        if (utilizator.isPresent()) {
            comanda.setClient(client);
        }
        final CosCumparaturi cosCumparaturi = shoppingCartService
                .adaugaCosCumparaturi(clientService.findByUtilizator(utilizator.get()));
        shoppingCartService.getProductsInCart()
                .forEach((produs, cantitate) -> {
                            cartItemRepository.save(
                                    new CartItem(cantitate * produs.getPret(),
                                            produs,
                                            cosCumparaturi,
                                            cantitate));
                            Produs produsActualizat = productService.findById(produs.getIdProdus()).get();
                            produsActualizat.setCantitate(produs.getCantitate() - cantitate);
                            productService.saveAndFlush(produsActualizat);
                        }
                );
        for (Map.Entry<Produs, Integer> produs : shoppingCartService.getProductsInCart().entrySet()) {
            pretTotalCosCumparaturi += produs.getKey().getPret() * produs.getValue();
        }
        cosCumparaturi.setPrice(pretTotalCosCumparaturi);
        comanda.setCosCumparaturi(cosCumparaturi);
        comanda.setDataComanda(new Date());
        comanda.setStatus("Comanda plasata");
        client.setCosCumparaturi(cosCumparaturi);
        shoppingCartService.clearProductsInCart();
        clientService.saveClient(client);
        comenziRepository.save(comanda);
    }

    public List<Produs> listaProduseDupaUltimulCosCumparaturi() {
        final List<Produs> produse = new ArrayList<>();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Utilizator> utilizator = userService.findByEmail(user.getUsername());
        Client client = clientService.findByUtilizator(utilizator.get());
        CosCumparaturi cosCumparaturi = client.getCosCumparaturi();
        List<CartItem> cartItems = cartItemRepository.findByCosCumparaturi(cosCumparaturi);
        cartItems.forEach(cartItem -> cartItem.getProdus().setCantitate(cartItem.getCantitate()));
        cartItems.forEach(cartItem -> cartItem.getProdus().setPret(cartItem.getProdus().getCantitate() * cartItem.getProdus().getPret()));
        cartItems.forEach(cartItem -> produse.add(cartItem.getProdus()));
        return produse;
    }

    public List<CartItem> cautaCosDupaIdComanda(Long idComanda) {
        CosCumparaturi cosCumparaturi = comenziRepository.findByIdComanda(idComanda).getCosCumparaturi();
        return cartItemRepository.findByCosCumparaturi(cosCumparaturi);
    }


}
