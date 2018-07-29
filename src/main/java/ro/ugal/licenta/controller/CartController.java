package ro.ugal.licenta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.ugal.licenta.service.ComenziService;
import ro.ugal.licenta.service.ProductService;
import ro.ugal.licenta.service.ShoppingCartService;



@Controller
public class CartController {

    private final ShoppingCartService shoppingCartService;

    private final ProductService productService;

    private final ComenziService comenziService;


    public CartController(ShoppingCartService shoppingCartService, ProductService productService, ComenziService comenziService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.comenziService = comenziService;
    }

    @GetMapping("/cart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        return modelAndView;
    }

    @GetMapping("/shoppingCart/addProduct/{productId}")
    public String addProductToCart(@PathVariable("productId") Long productId) {
        productService.findById(productId).ifPresent(shoppingCartService::addProduct);
        return "redirect:/cart";
    }

    @GetMapping("/shoppingCart/removeProduct/{productId}")
    public String removeProductFromCart(@PathVariable Long productId) {
        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
        return "redirect:/cart";
    }

    @PostMapping("/adaugaComanda")
    public String adaugaComanda() {
        comenziService.adaugaComanda();
        return "redirect:/home";
    }

    @RequestMapping(path = "/cautaCosDupaIdComanda={id}", method = RequestMethod.GET)
    public ModelAndView cautaCosDupaIdComanda(@PathVariable Long id, ModelAndView modelAndView) {
        modelAndView.setViewName("produseDupaIdComanda");
        modelAndView.addObject("cartItemList", comenziService.cautaCosDupaIdComanda(id));
        return modelAndView;
    }


}
