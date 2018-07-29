package ro.ugal.licenta;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ro.ugal.licenta.model.Produs;
import ro.ugal.licenta.repository.ProductRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

//    @Test
//    public void dacaSeGasesteAngajatDupaNume_atunciIntoarceAngajat() {
//        //date initiale
//        Produs produs = new Produs("testNume", "testDescriere",
//                3, 3, "testCategorie");
//        entityManager.persist(produs);
//        entityManager.flush();
//
//
//        Produs produsGasit = productRepository.findByNume(produs.getNume());
//
//        assertThat(produsGasit.getNume(), is(equalTo(produs.getNume())));
//
//    }
}
