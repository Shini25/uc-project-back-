package finance.uc_project.service.chefsUC;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import finance.uc_project.model.chefsUC.Uc;
import finance.uc_project.model.chefsUC.UcAttribution;
import finance.uc_project.model.chefsUC.UcMotDuChef;
import finance.uc_project.model.chefsUC.UcPhoto;
import finance.uc_project.repository.chefsUC.UcAttributionRepository;
import finance.uc_project.repository.chefsUC.UcMotDuChefRepository;
import finance.uc_project.repository.chefsUC.UcPhotoRepository;
import finance.uc_project.repository.chefsUC.UcRepository;

@Service
public class UcCreationService {

    @Autowired
    private UcRepository ucRepository;

    @Autowired
    private UcPhotoRepository ucPhotoRepository;

    @Autowired
    private UcAttributionRepository ucAttributionRepository;

    @Autowired
    private UcMotDuChefRepository ucMotDuChefRepository;

    @Transactional
    public Uc createUc(Uc uc, byte[] photo, List<String> attributions, List<String> motsDuChef) {
        // Set the createdAt field
        uc.setCreatedAt(LocalDateTime.now());

        // Save the UC entity
        Uc savedUc = ucRepository.save(uc);

        // Save the photo
        UcPhoto ucPhoto = new UcPhoto();
        ucPhoto.setUc(savedUc);
        ucPhoto.setPhoto(photo);
        ucPhotoRepository.save(ucPhoto);

        // Save the attributions
        int numeroAttribution = 1;
        for (String attribution : attributions) {
            UcAttribution ucAttribution = new UcAttribution();
            ucAttribution.setUc(savedUc);
            ucAttribution.setAttribution(attribution);
            ucAttribution.setNumeroAttribution(numeroAttribution++);
            ucAttributionRepository.save(ucAttribution);
        }

        // Save the mots du chef
        int numeroMotDuChef = 1;
        for (String motDuChef : motsDuChef) {
            UcMotDuChef ucMotDuChef = new UcMotDuChef();
            ucMotDuChef.setUc(savedUc);
            ucMotDuChef.setParagraphe(motDuChef);
            ucMotDuChef.setNumeroParagraphe(numeroMotDuChef++);
            ucMotDuChefRepository.save(ucMotDuChef);
        }

        return savedUc;
    }
}