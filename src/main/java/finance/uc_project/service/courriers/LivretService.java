package finance.uc_project.service.courriers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finance.uc_project.enums.courrier.LivretType;
import finance.uc_project.enums.courrier.TypeDocument;
import finance.uc_project.model.courriers.Livret;
import finance.uc_project.repository.courriers.LivretRepository;


@Service
public class LivretService {

    @Autowired
    private LivretRepository livretRepository;

    public List<Livret> getAllLivrets() {
        return livretRepository.findAll();
    }

    public Optional<Livret> getLivretById(Long id) {
        return livretRepository.findById(id);
    }

    public Livret createLivret(Livret livret) {
        return livretRepository.save(livret);
    }

    public Livret updateLivret(Livret livret) {
        return livretRepository.save(livret);
    }

    public void deleteLivret(Long id) {
        livretRepository.deleteById(id);
    }

    public Livret createLivretPersonalise(String titre, byte[] contenue, String typeDeContenue, String livretType) {
        Livret livret = new Livret();
        livret.setTitre(titre);
        livret.setContenue(contenue);
        livret.setType(LivretType.valueOf(livretType));
        livret.setDateCourrier(LocalDateTime.now());
        livret.setTypeContenue(typeDeContenue);
        livret.setTypeDocument(TypeDocument.LIVRET); 
        return livretRepository.save(livret);
    }
}
