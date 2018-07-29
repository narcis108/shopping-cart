package ro.ugal.licenta;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ro.ugal.licenta.model.Client;
import ro.ugal.licenta.repository.ClientRepository;
import ro.ugal.licenta.service.ClientService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
public class ClientServiceIntegrationTest {

    @TestConfiguration
    static class ClientServiceTestContextConfiguration {
        @Bean
        public ClientService clientService() {
            return new ClientService();
        }
    }

    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @Before
    public void setUp() {
        Client client = new Client("testNume",
                "testPrenume", "testTelefon");

        Mockito.when(clientRepository.findByNume(client.getNume())).thenReturn(client);
    }

    @Test
    public void candSeGasesteUnNumeVald_intoareClientGasit() {
        String nume = "testNume";
        Client clientGasit = clientService.findByNume(nume);

        assertThat(clientGasit.getNume(), is(equalTo(nume)));
    }
}
