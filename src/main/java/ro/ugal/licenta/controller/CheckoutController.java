package ro.ugal.licenta.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ro.ugal.licenta.model.Client;
import ro.ugal.licenta.model.Utilizator;
import ro.ugal.licenta.service.*;

import java.util.Optional;

@Controller

public class CheckoutController {

    UserService userService;
    ClientService clientService;
    AdresaLivrareService adresaLivrareService;
    ComenziService comenziService;
    ShoppingCartService shoppingCartService;

    public CheckoutController(UserService userService, ClientService clientService,
                              AdresaLivrareService adresaLivrareService,
                              ComenziService comenziService,
                              ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.clientService = clientService;
        this.adresaLivrareService = adresaLivrareService;
        this.comenziService = comenziService;
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(path = "/checkout", method = RequestMethod.GET)
    public ModelAndView checkout(ModelAndView model) {
        model.setViewName("checkout");

        final Optional<Utilizator> utilizator;
        final Client client;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null) {
            utilizator = userService.findByEmail(user.getUsername());
            if (utilizator.isPresent()) {
                client = clientService.findByUtilizator(utilizator.get());
                if (client != null) {
                    model.addObject("client", client);
                } else {
                    model.addObject("client", new Client());
                }
                model.addObject("utilizator", utilizator.get());
                model.addObject("products", shoppingCartService.getProductsInCart());
            }
        }
        return model;
    }

}
