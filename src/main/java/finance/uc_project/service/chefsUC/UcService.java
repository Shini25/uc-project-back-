package finance.uc_project.service.chefsUC;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finance.uc_project.model.chefsUC.Uc;
import finance.uc_project.repository.chefsUC.UcRepository;

@Service
public class UcService {

    @Autowired
    private UcRepository ucRepository;

    public List<Uc> getAllUcs() {
        return ucRepository.findAll();
    }

    public Optional<Uc> getUcById(String matricule) {
        return ucRepository.findById(matricule);
    }

    public Uc createUc(Uc uc) {
        return ucRepository.save(uc);
    }

    public Uc updateUc(Uc uc) {
        return ucRepository.save(uc);
    }

    public void deleteUc(String matricule) {
        ucRepository.deleteById(matricule);
    }
}