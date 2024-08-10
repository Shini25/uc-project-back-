package finance.uc_project.service.chefsUC;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import finance.uc_project.model.chefsUC.UcAttribution;
import finance.uc_project.repository.chefsUC.UcAttributionRepository;
import finance.uc_project.repository.chefsUC.UcRepository;

@Service
public class UcAttributionService {

    @Autowired
    private UcAttributionRepository ucAttributionRepository;

    @Autowired
    private UcRepository ucRepository;

    public List<UcAttribution> getAllAttributions() {
        return ucAttributionRepository.findAll();
    }

    public Optional<UcAttribution> getAttributionById(Long id) {
        return ucAttributionRepository.findById(id);
    }

    public UcAttribution createAttribution(UcAttribution attribution) {
        return ucAttributionRepository.save(attribution);
    }

    public UcAttribution updateAttribution(UcAttribution attribution) {
        return ucAttributionRepository.save(attribution);
    }

    public void deleteAttribution(Long id) {
        ucAttributionRepository.deleteById(id);
    }

    @Transactional
    public List<String> getAttributionsByUc(String ucId) {
        return ucRepository.findById(ucId)
                .map(uc -> uc.getAttributions().stream()
                        .map(UcAttribution::getAttribution)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public UcAttribution addAttributionToUc(String ucId, UcAttribution attribution) {
        return ucRepository.findById(ucId).map(uc -> {
            attribution.setUc(uc);
            return ucAttributionRepository.save(attribution);
        }).orElse(null);
    }
}