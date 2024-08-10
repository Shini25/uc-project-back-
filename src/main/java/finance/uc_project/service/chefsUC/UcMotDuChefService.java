package finance.uc_project.service.chefsUC;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import finance.uc_project.model.chefsUC.UcMotDuChef;
import finance.uc_project.repository.chefsUC.UcMotDuChefRepository;
import finance.uc_project.repository.chefsUC.UcRepository;

@Service
public class UcMotDuChefService {

    @Autowired
    private UcMotDuChefRepository ucMotDuChefRepository;

    @Autowired
    private UcRepository ucRepository;

    public List<UcMotDuChef> getAllMotDuChefs() {
        return ucMotDuChefRepository.findAll();
    }

    public Optional<UcMotDuChef> getMotDuChefById(Long id) {
        return ucMotDuChefRepository.findById(id);
    }

    public UcMotDuChef createMotDuChef(UcMotDuChef motDuChef) {
        return ucMotDuChefRepository.save(motDuChef);
    }

    public UcMotDuChef updateMotDuChef(UcMotDuChef motDuChef) {
        return ucMotDuChefRepository.save(motDuChef);
    }

    public void deleteMotDuChef(Long id) {
        ucMotDuChefRepository.deleteById(id);
    }

    @Transactional
    public List<String> getMotDuChefsByUc(String ucId) {
        return ucRepository.findById(ucId)
                .map(uc -> uc.getMotDuChefs().stream()
                        .map(UcMotDuChef::getParagraphe)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public UcMotDuChef addMotDuChefToUc(String ucId, UcMotDuChef motDuChef) {
        return ucRepository.findById(ucId).map(uc -> {
            motDuChef.setUc(uc);
            return ucMotDuChefRepository.save(motDuChef);
        }).orElse(null);
    }
}