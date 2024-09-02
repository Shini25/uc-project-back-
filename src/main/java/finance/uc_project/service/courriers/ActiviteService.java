package finance.uc_project.service.courriers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finance.uc_project.enums.courrier.ActiviteType;
import finance.uc_project.enums.courrier.TypeDocument;
import finance.uc_project.model.courriers.Activite;
import finance.uc_project.repository.courriers.ActiviteRepository;

@Service
public class ActiviteService {

    @Autowired
    private ActiviteRepository activiteRepository;

    public List<Activite> getAllActivites() {
        return activiteRepository.findAll();
    }

    public Activite createActivitePersonalise(String titre, byte[] contenue, String typeDeContenue, String activiteType) {
        Activite activite = new Activite();
        activite.setTitre(titre);
        activite.setContenue(contenue);
        activite.setType(ActiviteType.valueOf(activiteType));
        activite.setDateCourrier(LocalDateTime.now());
        activite.setTypeContenue(typeDeContenue);
        activite.setTypeDocument(TypeDocument.LIVRET); 
        return activiteRepository.save(activite);
    }
}
