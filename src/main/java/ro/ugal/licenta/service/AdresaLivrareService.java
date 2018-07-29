package ro.ugal.licenta.service;

import org.springframework.stereotype.Service;
import ro.ugal.licenta.model.AdresaLivrare;
import ro.ugal.licenta.repository.AdresaLivrareRepository;

@Service
public class AdresaLivrareService {

    AdresaLivrareRepository adresaLivrareRepository;

    public AdresaLivrareService(AdresaLivrareRepository adresaLivrareRepository) {
        this.adresaLivrareRepository = adresaLivrareRepository;
    }

    public void saveAdresaLivrare(AdresaLivrare adresaLivrare) {
        adresaLivrareRepository.saveAndFlush(adresaLivrare);
    }
}
