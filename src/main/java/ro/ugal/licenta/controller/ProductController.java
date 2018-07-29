package ro.ugal.licenta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.ugal.licenta.model.Categorie;
import ro.ugal.licenta.model.Produs;
import ro.ugal.licenta.repository.CategorieRepository;
import ro.ugal.licenta.service.ProductService;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    ProductService productService;
    CategorieRepository categorieRepository;

    public ProductController(ProductService productService, CategorieRepository categorieRepository) {
        this.productService = productService;
        this.categorieRepository = categorieRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/afiseazaToateProdusele")
    public ModelAndView afiseazaToateProdusele(ModelAndView model) {
        model.setViewName("afisareProduse");
        model.addObject("products", productService.findByCantitateGreaterThan(10));
        return model;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/afiseazaProduse")
    public ModelAndView afiseazaProduse(ModelAndView modelAndView) {
        modelAndView.setViewName("afisareProduse");
        modelAndView.addObject("products", productService.findAllProducts());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/afiseazaProduseDupaCategorie={categorie}")
    public ModelAndView afiseazaProduseDupaCategorie(@PathVariable String categorie,
                                                     ModelAndView modelAndView) {
        Categorie categorieDBObject = categorieRepository.findByNume(categorie).get();
        List<Produs> listaProduse = productService.findProductsByCategory(categorieDBObject);
        modelAndView.addObject("products", listaProduse);
        modelAndView.addObject("categorii", categorieRepository.findAll());
        modelAndView.setViewName("afisareProduse");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/afiseazaProduseDupaNume")
    public ModelAndView afiseazaProduseDupaNume(@RequestParam String nume,
                                                ModelAndView modelAndView) {
        List<Produs> listaProduse = productService.findProductsByNume(nume);
        modelAndView.addObject("products", listaProduse);
        modelAndView.addObject("categorii", categorieRepository.findAll());
        modelAndView.setViewName("afisareProduse");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/adaugareProdusNou")
    public String adaugaProdusNou(@Valid @ModelAttribute Produs produs) throws Exception {
        List<Produs> produsExistent = productService.findProductsByNume(produs.getNume());
        if (produsExistent.size() > 0) {
            throw new Exception("Produs Existent");
        }
        produs.setCategorie(categorieRepository.findByNume(produs.getCategorie().getNume()).get());
        productService.saveProduct(produs);
        return "redirect:/home";
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/stergeProdusDupaId/{id}")
    public void stergeProdusDupaId(@PathVariable Long id) throws Exception {
        Optional<Produs> produsExistent = productService.findById(id);
        if (!produsExistent.isPresent()) {
            throw new Exception("Produs inexistent!");
        }
        productService.deleteProductById(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/adaugaProdusNou")
    public ModelAndView adaugaProdusNou(ModelAndView model) {
        model.setViewName("adaugaProdus");
        model.addObject("produs", new Produs());
        model.addObject("categorii", categorieRepository.findAll());
        return model;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/detaliiProdusId={id}")
    public ModelAndView detaliiProdus(@PathVariable Long id, ModelAndView modelAndView) {
        modelAndView.setViewName("detalii-produs");
        modelAndView.addObject("produs", productService.findById(id).get());
        return modelAndView;
    }


}
