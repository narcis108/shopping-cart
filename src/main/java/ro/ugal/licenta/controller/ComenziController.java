package ro.ugal.licenta.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ro.ugal.licenta.model.Client;
import ro.ugal.licenta.model.ClientComenzi;
import ro.ugal.licenta.repository.ComenziRepository;
import ro.ugal.licenta.service.ClientService;
import ro.ugal.licenta.service.UserService;

import java.util.List;

@Controller
public class ComenziController {

    ClientService clientService;
    UserService userService;
    ComenziRepository comenziRepository;

    public ComenziController(ClientService clientService, UserService userService, ComenziRepository comenziRepository) {
        this.clientService = clientService;
        this.userService = userService;
        this.comenziRepository = comenziRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/comenzi")
    public ModelAndView comenzi(ModelAndView model) {
        model.setViewName("comenzi");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = clientService.findByUtilizator(userService.findByEmail(user.getUsername()).get());
        List<ClientComenzi> listaComenzi = comenziRepository.findByClient(client);

        model.addObject("listaComenzi", listaComenzi);

        return model;
    }

}
