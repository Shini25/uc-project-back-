package finance.uc_project.service.courriers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finance.uc_project.enums.courrier.PtaType;
import finance.uc_project.model.courriers.Pta;
import finance.uc_project.repository.courriers.PtaRepository;

@Service
public class PtaService {

    @Autowired
    private PtaRepository ptaRepository;

       public Pta  createPtaPersonalise(String titre, byte[] contenue, String typeDeContenue, String ptaType) {
        Pta pta = new Pta();
        pta.setTitre(titre);
        pta.setContenue(contenue);
        pta.setType(PtaType.valueOf(ptaType));
        pta.setDateInsertion(LocalDateTime.now());
        pta.setTypeContenue(typeDeContenue);
        return ptaRepository.save(pta);
    }
}
