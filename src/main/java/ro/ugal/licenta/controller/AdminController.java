package ro.ugal.licenta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.ugal.licenta.model.CartItem;
import ro.ugal.licenta.model.Categorie;
import ro.ugal.licenta.model.ClientComenzi;
import ro.ugal.licenta.model.Produs;
import ro.ugal.licenta.repository.CartItemRepository;
import ro.ugal.licenta.repository.CategorieRepository;
import ro.ugal.licenta.repository.ComenziRepository;
import ro.ugal.licenta.service.ClientService;
import ro.ugal.licenta.service.ProductService;
import ro.ugal.licenta.service.UserService;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {
    UserService userService;
    ClientService clientService;
    ComenziRepository comenziRepository;
    ProductService productService;
    CartItemRepository cartItemRepository;
    CategorieRepository categorieRepository;

    public AdminController(UserService userService, ClientService clientService, ComenziRepository comenziRepository, ProductService productService, CartItemRepository cartItemRepository, CategorieRepository categorieRepository) {
        this.userService = userService;
        this.clientService = clientService;
        this.comenziRepository = comenziRepository;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
        this.categorieRepository = categorieRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/admin-comenzi")
    public ModelAndView toateComenzile(ModelAndView modelAndView) {
        modelAndView.setViewName("comenzi-admin");

        List<ClientComenzi> listaComenzi = comenziRepository.findAll();

        modelAndView.addObject("listaComenzi", listaComenzi);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/schimbaStatusComanda")
    public String schimbaStatusComanda(@RequestParam String statusComanda,
                                       @RequestParam Integer idComanda) {

        ClientComenzi comanda = comenziRepository.findByIdComanda(Long.valueOf(idComanda));

        if (statusComanda.equals("Anulat")) {
            List<CartItem> cartItems = cartItemRepository.findByCosCumparaturi(comanda.getCosCumparaturi());
            cartItems.forEach(cartItem -> {
                Produs produs = productService.findById(cartItem.getProdus().getIdProdus()).get();
                produs.setCantitate(produs.getCantitate() + cartItem.getCantitate());
                productService.saveAndFlush(produs);
            });
        }
        comanda.setStatus(statusComanda);

        comenziRepository.save(comanda);

        return "redirect:/admin-comenzi";
    }

    @GetMapping("/adaugaCategorie")
    public ModelAndView adaugaCategorie(ModelAndView model) {
        model.setViewName("adaugaCategorie");

        model.addObject("categorie", new Categorie());

        return model;
    }

    @PostMapping("/adaugaCategorie")
    public String adaugaCategorie(@ModelAttribute Categorie categorie) {
        categorieRepository.save(categorie);
        return "redirect:/home";
    }
}
