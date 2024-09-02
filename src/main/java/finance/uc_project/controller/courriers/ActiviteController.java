package finance.uc_project.controller.courriers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import finance.uc_project.model.courriers.Activite;
import finance.uc_project.service.courriers.ActiviteService;

@RestController
@RequestMapping("/api/activites")
public class ActiviteController {

    @Autowired
    private ActiviteService activiteService;

    // @GetMapping
    // public ResponseEntity<List<Activite>> getAllActivites() {
    //     List<Activite> activites = activiteService.getAllActivites();
    //     return ResponseEntity.ok(activites);
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<Activite> getActiviteById(@PathVariable Long id) {
    //     Optional<Activite> activite = activiteService.getActiviteById(id);
    //     return activite.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    // }

    // @PostMapping("/insertion")
    // public ResponseEntity<Activite> createActivite(@RequestBody Activite activite) {
    //     Activite createdActivite = activiteService.createActivite(activite);
    //     return new ResponseEntity<>(createdActivite, HttpStatus.CREATED);
    // }

    @PostMapping("/insertion/personalise")
        public ResponseEntity<Activite> createActivitePersonalise(
                @RequestPart("titre") String titre,
                @RequestPart("contenue") String base64Contenue,
                @RequestPart("typeDeLivret") String typeDeLivret,
                @RequestPart("typeDeContenue") String typeDeContenue ) {
            try {
                String Base64Contenue = base64Contenue.split(",")[1];
                byte[] contenueBytes = Base64.getDecoder().decode(Base64Contenue);
                Activite createActivite = activiteService.createActivitePersonalise(titre, contenueBytes, typeDeContenue, typeDeLivret);
                return ResponseEntity.ok(createActivite);
            } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Livret> updateLivret(@PathVariable Long id, @RequestBody Livret pta) {
    //     if (!livretService.getLivretById(id).isPresent()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     pta.setId(id);
    //      updatedLivret = livretService.updateLivret(pta);
    //     return ResponseEntity.ok(updatedLivret);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteLivret(@PathVariable Long id) {
    //     if (!ptaService.getLivretById(id).isPresent()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     ptaService.deleteLivret(id);
    //     return ResponseEntity.noContent().build();
    // }
}
