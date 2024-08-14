package finance.uc_project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finance.uc_project.enums.TypeDePta;
import finance.uc_project.model.Pta;
import finance.uc_project.repository.PtaRepository;

@Service
public class PtaService {

    @Autowired
    private PtaRepository ptaRepository;

    public List<Pta> getAllPta() {
        return ptaRepository.findAll();
    }

    public Optional<Pta> getPtaById(Long id) {
        return ptaRepository.findById(id);
    }

    public Pta createPta(Pta pta) {
        return ptaRepository.save(pta);
    }

    public Pta createPtaPersonalise(String titre, byte[] contenue, String typeDePta) {

        Pta pta = new Pta();
        pta.setTitre(titre);
        pta.setContenue(contenue);
        pta.setTypeDePta(TypeDePta.valueOf(typeDePta));
        pta.setDatePta(LocalDateTime.now());
        return ptaRepository.save(pta);
    }

    public Pta updatePta(Pta pta) {
        return ptaRepository.save(pta);
    }

    public void deletePta(Long id) {
        ptaRepository.deleteById(id);
    }
}
