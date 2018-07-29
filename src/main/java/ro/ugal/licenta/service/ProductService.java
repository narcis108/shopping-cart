package ro.ugal.licenta.service;

import org.springframework.stereotype.Component;
import ro.ugal.licenta.model.Categorie;
import ro.ugal.licenta.model.Produs;
import ro.ugal.licenta.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Produs> findAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Produs produs) {
        productRepository.save(produs);
    }

    public void saveAndFlush(Produs produs){
        productRepository.saveAndFlush(produs);
    }

    public Optional<Produs> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Produs> findProductsByCategory(Categorie categorie) {
        return productRepository.findByCategorie(categorie);
    }

    public List<Produs> findProductsByNume(String nume) {
        return productRepository.findByNumeLike(nume);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Produs> findByCantitateGreaterThan(Integer cantitate) {
        return productRepository.findByCantitateGreaterThan(cantitate);
    }

    public List<Produs> findLatestProducts(){
        return productRepository.findAllByOrderByIdProdusDesc();
    }
}

