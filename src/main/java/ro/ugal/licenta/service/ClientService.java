package ro.ugal.licenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ugal.licenta.model.Client;
import ro.ugal.licenta.model.Utilizator;
import ro.ugal.licenta.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Client findByNume(String name) {
        return clientRepository.findByNume(name);
    }

    public Client findByUtilizator(Utilizator utilizator) {
        return clientRepository.findByUtilizator(utilizator);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }
}
