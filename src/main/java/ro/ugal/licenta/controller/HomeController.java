package ro.ugal.licenta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.ugal.licenta.model.Client;
import ro.ugal.licenta.model.Produs;
import ro.ugal.licenta.repository.CategorieRepository;
import ro.ugal.licenta.service.ComenziService;
import ro.ugal.licenta.service.ProductService;

import java.util.List;
import java.util.stream.Stream;

@Controller
public class HomeController {
    ProductService productService;
    CategorieRepository categorieRepository;

    public HomeController(ProductService productService, CategorieRepository categorieRepository) {
        this.productService = productService;
        this.categorieRepository = categorieRepository;
    }

    @GetMapping(path = {"/", "/home"})
    public String homePage(Model model) {
        model.addAttribute("products", productService.findLatestProducts().subList(0, 6));
        model.addAttribute("categorii", categorieRepository.findAll());
        return "index";
    }

}
