package ro.ugal.licenta.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.ugal.licenta.model.AdresaLivrare;
import ro.ugal.licenta.model.Client;
import ro.ugal.licenta.service.AdresaLivrareService;
import ro.ugal.licenta.service.ClientService;
import ro.ugal.licenta.service.UserService;

import javax.validation.Valid;

@Controller
public class AdresaController {

    UserService userService;
    ClientService clientService;
    AdresaLivrareService adresaLivrareService;

    public AdresaController(UserService userService, ClientService clientService, AdresaLivrareService adresaLivrareService) {
        this.userService = userService;
        this.clientService = clientService;
        this.adresaLivrareService = adresaLivrareService;
    }

    @RequestMapping(path = "/salveazaAdresaLivrare", method = RequestMethod.POST)
    public String salveazaAdresa(@Valid @ModelAttribute Client client) {

        final AdresaLivrare adresaLivrare = client.getAdresaLivrare();
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        client = clientService.findByUtilizator(userService.
                findByEmail(user.getUsername()).get());
        adresaLivrareService.saveAdresaLivrare(adresaLivrare);


        client.setAdresaLivrare(adresaLivrare);
        clientService.saveClient(client);


        return "redirect:/checkout";
    }
}
