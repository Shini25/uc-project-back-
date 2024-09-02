package finance.uc_project.controller.courriers;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import finance.uc_project.model.courriers.Livret;
import finance.uc_project.service.courriers.LivretService;

@RestController
@RequestMapping("/api/livrets")
public class LivretController {

    @Autowired
    private LivretService livretService;

    @GetMapping
    public ResponseEntity<List<Livret>> getAllLivrets() {
        List<Livret> livrets = livretService.getAllLivrets();
        return ResponseEntity.ok(livrets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livret> getLivretById(@PathVariable Long id) {
        Optional<Livret> livret = livretService.getLivretById(id);
        return livret.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/insertion")
    public ResponseEntity<Livret> createLivret(@RequestBody Livret livret) {
        Livret createdLivret = livretService.createLivret(livret);
        return new ResponseEntity<>(createdLivret, HttpStatus.CREATED);
    }

    @PostMapping("/insertion/personalise")
        public ResponseEntity<Livret> createLivretPersonalise(
                @RequestPart("titre") String titre,
                @RequestPart("contenue") String base64Contenue,
                @RequestPart("typeDeLivret") String typeDeLivret,
                @RequestPart("typeDeContenue") String typeDeContenue ) {
            try {

                String Base64Contenue = base64Contenue.split(",")[1];
                byte[] contenueBytes = Base64.getDecoder().decode(Base64Contenue);
                Livret createLivret = livretService.createLivretPersonalise(titre, contenueBytes, typeDeContenue, typeDeLivret);
                return ResponseEntity.ok(createLivret);
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
