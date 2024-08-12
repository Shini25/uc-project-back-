package finance.uc_project.service.chefs;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import finance.uc_project.model.chefs.attribution;
import finance.uc_project.model.chefs.infoBase;
import finance.uc_project.model.chefs.motDuChef;
import finance.uc_project.model.chefs.photo;
import finance.uc_project.repository.chefs.attributionRepository;
import finance.uc_project.repository.chefs.infoBaseRepository;
import finance.uc_project.repository.chefs.motDuChefRepository;
import finance.uc_project.repository.chefs.photoRepository;

@Service
public class creationChefService {

    @Autowired
    private infoBaseRepository chefRepository;

    @Autowired
    private photoRepository chefPhotoRepository;

    @Autowired
    private attributionRepository chefAttributionRepository;

    @Autowired
    private motDuChefRepository chefMotDuChefRepository;

    @Transactional
    public infoBase createChef(infoBase chef, byte[] photo, List<String> attributions, List<String> motsDuChef) {
        // Set the createdAt field
        chef.setCreatedAt(LocalDateTime.now());

        // Save the chef entity
        infoBase savedChef = chefRepository.save(chef);

        // Save the photo
        photo chefPhoto = new photo();
        chefPhoto.setChef(savedChef);
        chefPhoto.setPhoto(photo);
        chefPhotoRepository.save(chefPhoto);

        // Save the attributions
        int numeroAttribution = 1;
        for (String attribution : attributions) {
            attribution chefAttribution = new attribution();
            chefAttribution.setChef(savedChef);
            chefAttribution.setAttribution(attribution);
            chefAttribution.setNumeroAttribution(numeroAttribution++);
            chefAttributionRepository.save(chefAttribution);
        }

        // Save the mots du chef
        int numeroMotDuChef = 1;
        for (String motDuChef : motsDuChef) {
            motDuChef chefMotDuChef = new motDuChef();
            chefMotDuChef.setChef(savedChef);
            chefMotDuChef.setParagraphe(motDuChef);
            chefMotDuChef.setNumeroParagraphe(numeroMotDuChef++);
            chefMotDuChefRepository.save(chefMotDuChef);
        }

        return savedChef;
    }
}