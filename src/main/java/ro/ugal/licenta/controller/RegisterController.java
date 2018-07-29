package ro.ugal.licenta.controller;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ro.ugal.licenta.model.Client;
import ro.ugal.licenta.model.CosCumparaturi;
import ro.ugal.licenta.model.Utilizator;
import ro.ugal.licenta.repository.ClientRepository;
import ro.ugal.licenta.repository.RoleRepository;
import ro.ugal.licenta.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Controller
public class RegisterController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private ClientRepository clientRepository;

    public RegisterController(UserService userService, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ClientRepository clientRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.clientRepository = clientRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView) {
        modelAndView.addObject("utilizator", new Utilizator());
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegistrationForm(@Valid @ModelAttribute Utilizator utilizator,
                                                BindingResult bindingResult,
                                                HttpServletRequest request, ModelAndView modelAndView) {

        Optional<Utilizator> userExists = userService.findByEmail(utilizator.getEmail());

        if (userExists.isPresent()) {
            modelAndView.addObject("alreadyRegisteredMessage", "Oops!  " +
                    "Exista deja un utilizator " +
                    "cu acest email!");
            modelAndView.setViewName("register");
            bindingResult.reject("email");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("bindingError", "Eroare la binding");
            modelAndView.setViewName("register");
        } else {

            utilizator.setEnabled(false);
            utilizator.setConfirmationToken(UUID.randomUUID().toString());
            utilizator.setRoles(Collections.singletonList(roleRepository.findByRole("ROLE_USER")));

            userService.saveUserWithoutPassword(utilizator);

            modelAndView.addObject("confirmationMessage",
                    "Token-ul de inregistrare (cel care trebuia sa ajunga pe email) este :"
                            + utilizator.getConfirmationToken());
            modelAndView.addObject("link", "http://localhost:8080/confirm?token="
                    + utilizator.getConfirmationToken());
            modelAndView.setViewName("register");
        }

        return modelAndView;
    }

    @RequestMapping(path = "/confirm", method = RequestMethod.GET)
    public ModelAndView showConfirmationPage(ModelAndView modelAndView,
                                             @RequestParam("token") String token) {

        Utilizator utilizator = userService.findByConfirmationToken(token);

        if (utilizator == null) {
            modelAndView.addObject("invalidToken",
                    "Token-ul este invalid!");
        } else {
            modelAndView.addObject("confirmationToken",
                    utilizator.getConfirmationToken());
        }

        modelAndView.setViewName("confirmation");
        return modelAndView;
    }

    @RequestMapping(path = "/processPassword", method = RequestMethod.POST)
    public ModelAndView processPassword(ModelAndView modelAndView,
                                        @RequestParam String parola,
                                        @RequestParam String token,
                                        HttpServletRequest request) throws ServletException {

        Utilizator utilizator = userService.findByConfirmationToken(token);
        utilizator.setPassword(parola);


        Client client = new Client();
        client.setUtilizator(utilizator);

        clientRepository.save(client);
        userService.saveUser(utilizator);

        modelAndView.setViewName("/index");
        return modelAndView;
    }


}
