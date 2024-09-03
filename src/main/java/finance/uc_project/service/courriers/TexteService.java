package finance.uc_project.service.courriers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finance.uc_project.enums.courrier.TextesType;
import finance.uc_project.model.User_account;
import finance.uc_project.model.courriers.Texte;
import finance.uc_project.repository.UserRepository;
import finance.uc_project.repository.courriers.TexteRepository;

@Service
public class TexteService {

    @Autowired
    private TexteRepository texteRepository;

    @Autowired
    private UserRepository userRepository;

       public Texte  createTextePersonalise(String titre, byte[] contenue, String typeDeContenue, String texteType, String userId) {
        Texte texte = new Texte();
        texte.setTitre(titre);
        texte.setContenue(contenue);
        texte.setType(TextesType.valueOf(texteType));
        texte.setDateInsertion(LocalDateTime.now());
        texte.setTypeContenue(typeDeContenue);
        User_account user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + userId));
        texte.setUserId(user);
        return texteRepository.save(texte);
    }
}
