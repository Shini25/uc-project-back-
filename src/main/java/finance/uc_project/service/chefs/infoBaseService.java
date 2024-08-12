package finance.uc_project.service.chefs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import finance.uc_project.enums.TypeDeChef;
import finance.uc_project.model.chefs.infoBase;
import finance.uc_project.repository.chefs.infoBaseRepository;

@Service
public class infoBaseService {

    @Autowired
    private infoBaseRepository chefRepository;

    public List<infoBase> getAllChefs() {
        return chefRepository.findAll();
    }

    public Optional<infoBase> getChefById(String matricule) {
        return chefRepository.findById(matricule);
    }

    public infoBase createChef(infoBase chef) {
        return chefRepository.save(chef);
    }

    public infoBase updateChef(infoBase chef) {
        return chefRepository.save(chef);
    }

    public void deleteChef(String matricule) {
        chefRepository.deleteById(matricule);
    }

    public List<infoBase> getAllChefsOrdered(String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by("createdAt").ascending() : Sort.by("createdAt").descending();
        return chefRepository.findAll(sort);
    }

    // New methods to filter by TypeDeChef
    public List<infoBase> getChefsByTypeDeChef(String typeDeChef) {
        TypeDeChef type = TypeDeChef.valueOf(typeDeChef);
        return chefRepository.findByTypeDeChef(type);
    }
}