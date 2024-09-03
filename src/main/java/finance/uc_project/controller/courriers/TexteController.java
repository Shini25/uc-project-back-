package finance.uc_project.controller.courriers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // @GetMapping
    // public ResponseEntity<List<Pta>> getAllPtas() {
    //     List<Pta> livrets = ptaService.getAllPtas();
    //     return ResponseEntity.ok(livrets);
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<Pta> getPtaById(@PathVariable Long id) {
    //     Optional<Pta> livret = ptaService.getPtaById(id);
    //     return livret.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    // }

    // @PostMapping("/insertion")
    // public ResponseEntity<Pta> createPta(@RequestBody Pta livret) {
    //     Pta createdPta = ptaService.createPta(livret);
    //     return new ResponseEntity<>(createdPta, HttpStatus.CREATED);
    // }

    @PostMapping("/insertion/personalise")
        public ResponseEntity<Texte> createTextePersonalise(
                @RequestPart("titre") String titre,
                @RequestPart("contenue") String base64Contenue,
                @RequestPart("typeDeTexte") String typeDeTexte,
                @RequestPart("typeDeContenue") String typeDeContenue,
                @RequestPart("userId") String userId ) {
            try {

                String Base64Contenue = base64Contenue.split(",")[1];
                byte[] contenueBytes = Base64.getDecoder().decode(Base64Contenue);
                Texte createTexte = texteService.createTextePersonalise(titre, contenueBytes, typeDeContenue, typeDeTexte, userId);
                return ResponseEntity.ok(createTexte);
            } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

}
