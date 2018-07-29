package ro.ugal.licenta.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.ugal.licenta.model.Client;
import ro.ugal.licenta.model.Utilizator;
import ro.ugal.licenta.service.ClientService;
import ro.ugal.licenta.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class ClientController {

    ClientService clientService;
    UserService userService;


    public ClientController(ClientService clientService, UserService userService) {
        this.clientService = clientService;
        this.userService = userService;
    }

    @RequestMapping(path = "/registerClient", method = RequestMethod.POST)
    public String saveClient(@Valid @ModelAttribute Client client) {
        final Client clientExistent;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Utilizator> utilizator = userService.findByEmail(user.getUsername());
        clientExistent = clientService.findByUtilizator(utilizator.get());
        clientExistent.setNume(client.getNume());
        clientExistent.setPrenume(client.getPrenume());
        clientExistent.setTelefon(client.getTelefon());
        clientService.saveClient(clientExistent);
        return "redirect:/checkout";
    }
}
