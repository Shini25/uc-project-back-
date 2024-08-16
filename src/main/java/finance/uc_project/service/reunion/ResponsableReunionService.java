package finance.uc_project.service.reunion;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import finance.uc_project.model.reunion.ResponsableReunion;
import finance.uc_project.repository.reunion.InfoReunionBaseRepository;
import finance.uc_project.repository.reunion.ResponsableReunionRepository;

@Service
public class ResponsableReunionService {

    @Autowired
    private ResponsableReunionRepository responsableReunionRepository;

    @Autowired
    private InfoReunionBaseRepository infoReunionBaseRepository;

    public List<ResponsableReunion> getAllResponsableReunions() {
        return responsableReunionRepository.findAll();
    }

    public ResponsableReunion getResponsableReunionById(Long id) {
        return responsableReunionRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<String> getResponsableByReunion(Long reunionId) {
        return infoReunionBaseRepository.findById(reunionId)
                .map(reunion -> reunion.getResponsables().stream()
                        .map(participant -> participant.getChef().getMatricule())
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public ResponsableReunion createResponsableReunion(ResponsableReunion responsableReunion) {
        return responsableReunionRepository.save(responsableReunion);
    }

    public ResponsableReunion updateResponsableReunion(ResponsableReunion responsableReunion) {
        return responsableReunionRepository.save(responsableReunion);
    }

    public void deleteResponsableReunion(Long id) {
        responsableReunionRepository.deleteById(id);
    }

}
