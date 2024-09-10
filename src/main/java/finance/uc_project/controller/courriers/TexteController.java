package finance.uc_project.controller.courriers;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import finance.uc_project.model.courriers.Texte;
import finance.uc_project.service.courriers.TexteService;

@RestController
@RequestMapping("/api/textes")
public class TexteController {

    @Autowired
    private TexteService texteService;


    @PostMapping("/insertion/personalise")
        public ResponseEntity<Texte> createTextePersonalise(
                @RequestPart("titre") String titre,
                @RequestPart("contenue") String base64Contenue,
                @RequestPart("type") String type,
                @RequestPart("typeDeContenue") String typeDeContenue,
                @RequestPart("userId") String userId ) {
            try {

                String Base64Contenue = base64Contenue.split(",")[1];
                byte[] contenueBytes = Base64.getDecoder().decode(Base64Contenue);
                Texte createTexte = texteService.createTextePersonalise(titre, contenueBytes, typeDeContenue, type, userId);
                return ResponseEntity.ok(createTexte);
            } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    // crud
    @GetMapping("/{id}")
    public ResponseEntity<Texte> getTexteById(@PathVariable Long id) {
        Optional<Texte> texte = texteService.getTexteById(id);
        return texte.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Texte>> getAllTextes() {
        List<Texte> textes = texteService.getAllTextes();
        return ResponseEntity.ok(textes);
    }
}
