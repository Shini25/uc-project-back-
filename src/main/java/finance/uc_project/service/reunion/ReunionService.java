package finance.uc_project.service.reunion;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import finance.uc_project.model.chefs.infoBase;
import finance.uc_project.model.reunion.InfoReunionBase;
import finance.uc_project.model.reunion.OrdreDuJour;
import finance.uc_project.model.reunion.Participant;
import finance.uc_project.model.reunion.ResponsableReunion;
import finance.uc_project.repository.chefs.infoBaseRepository;
import finance.uc_project.repository.reunion.InfoReunionBaseRepository;
import finance.uc_project.repository.reunion.OrdreDuJourRepository;
import finance.uc_project.repository.reunion.ParticipantRepository;
import finance.uc_project.repository.reunion.ResponsableReunionRepository;

@Service
public class ReunionService {

    @Autowired
    private InfoReunionBaseRepository infoReunionBaseRepository;

    @Autowired
    private OrdreDuJourRepository ordreDuJourRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private ResponsableReunionRepository responsableReunionRepository;

    @Autowired
    private infoBaseRepository chefRepository;

    @Transactional
    public InfoReunionBase planifierReunion(String titre, LocalDateTime dateReunion, String lieu, String objet, List<String> ordreDuJourDescriptions, List<String> responsablesMatricules, List<String> participantsMatricules) {
        // Create and save InfoReunionBase
        InfoReunionBase reunion = new InfoReunionBase();
        reunion.setTitre(titre);
        reunion.setDateCreation(LocalDateTime.now());
        reunion.setDateReunion(dateReunion);
        reunion.setLieu(lieu);
        reunion.setObjet(objet);
        InfoReunionBase savedReunion = infoReunionBaseRepository.save(reunion);

        // Add ordre du jour
        for (String description : ordreDuJourDescriptions) {
            OrdreDuJour ordreDuJour = new OrdreDuJour();
            ordreDuJour.setDescription(description);
            ordreDuJour.setInfoReunionBase(savedReunion);
            ordreDuJourRepository.save(ordreDuJour);
        }

        // Add responsables
        for (String matricule : responsablesMatricules) {
            infoBase chef = chefRepository.findById(matricule).orElseThrow(() -> new IllegalArgumentException("Invalid chef matricule: " + matricule));
            ResponsableReunion responsable = new ResponsableReunion();
            responsable.setInfoReunionBase(savedReunion);
            responsable.setChef(chef);
            responsableReunionRepository.save(responsable);
        }

        // Add participants
        for (String matricule : participantsMatricules) {
            infoBase chef = chefRepository.findById(matricule).orElseThrow(() -> new IllegalArgumentException("Invalid chef matricule: " + matricule));
            Participant participant = new Participant();
            participant.setInfoReunionBase(savedReunion);
            participant.setChef(chef);
            participantRepository.save(participant);
        }

        return savedReunion;
    }

    public List<InfoReunionBase> getAllReunions() {
        return infoReunionBaseRepository.findAll();
    }

    public InfoReunionBase getReunionById(Long id) {
        return infoReunionBaseRepository.findById(id).orElse(null);
    }

    public InfoReunionBase updateReunion(InfoReunionBase reunion) {
        return infoReunionBaseRepository.save(reunion);
    }

    public void deleteReunion(Long id) {
        infoReunionBaseRepository.deleteById(id);
    }

    
}