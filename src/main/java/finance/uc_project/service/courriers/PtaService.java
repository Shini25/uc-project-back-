package finance.uc_project.service.courriers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finance.uc_project.enums.courrier.PtaType;
import finance.uc_project.enums.courrier.TypeDocument;
import finance.uc_project.model.User_account;
import finance.uc_project.model.courriers.Pta;
import finance.uc_project.repository.UserRepository;
import finance.uc_project.repository.courriers.PtaRepository;

@Service
public class PtaService {

    @Autowired
    private PtaRepository ptaRepository;

    @Autowired
    private UserRepository userRepository;

       public Pta  createPtaPersonalise(String titre, byte[] contenue, String typeDeContenue, String ptaType, String userId) {
        Pta pta = new Pta();
        pta.setTitre(titre);
        pta.setContenue(contenue);
        pta.setType(PtaType.valueOf(ptaType));
        pta.setDateInsertion(LocalDateTime.now());
        pta.setTypeContenue(typeDeContenue);
        pta.setTypeDocument(TypeDocument.PTA);
        User_account user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + userId));
        pta.setUserId(user);
        return ptaRepository.save(pta);
    }

    //get all
    public List<Pta> getAllPta() {
        return ptaRepository.findAll();
    }

    public Optional<Pta> getPtaById(Long id) {
        return ptaRepository.findById(id);
    }

    public Pta createPta(Pta pta) {
        return ptaRepository.save(pta);
    }
    
    public Pta updatePta(Long id, Pta pta) {
        Pta existingPta = ptaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pta id: " + id));
        existingPta.setTitre(pta.getTitre());
        existingPta.setContenue(pta.getContenue());
        existingPta.setType(pta.getType());
        existingPta.setTypeContenue(pta.getTypeContenue());
        existingPta.setUserId(pta.getUser_account());
        return ptaRepository.save(existingPta);
    }

    public void deletePta(Long id) {
        ptaRepository.deleteById(id);
    }
}
