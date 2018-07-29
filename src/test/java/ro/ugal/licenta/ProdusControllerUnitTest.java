package ro.ugal.licenta;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ro.ugal.licenta.controller.ProductController;
import ro.ugal.licenta.model.Produs;
import ro.ugal.licenta.service.ProductService;

import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProdusControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

//    @Test
//    public void dacaSeApeleazaController_intoarceJsonCuToateProdusele() throws Exception {
//        Produs produs = new Produs("testProdus", "testDescriere",
//                2, BigDecimal.ONE, "testCategorie");
//        List<Produs> toateProdusele = Arrays.asList(produs);
//
//        BDDMockito.given(productService.findAllProducts()).willReturn(toateProdusele);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/afiseazaToateProdusele"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nume", CoreMatchers.is(produs.getNume())));
//
//
//    }
}
