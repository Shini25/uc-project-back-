package finance.uc_project.controller.courriers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import finance.uc_project.model.courriers.Pta;
import finance.uc_project.service.courriers.PtaService;

@RestController
@RequestMapping("/api/ptas")
public class PtaController {

    @Autowired
    private PtaService ptaService;

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
        public ResponseEntity<Pta> createPtaPersonalise(
                @RequestPart("titre") String titre,
                @RequestPart("contenue") String base64Contenue,
                @RequestPart("typeDePta") String typeDePta,
                @RequestPart("typeDeContenue") String typeDeContenue ) {
            try {

                String Base64Contenue = base64Contenue.split(",")[1];
                byte[] contenueBytes = Base64.getDecoder().decode(Base64Contenue);
                Pta createPta = ptaService.createPtaPersonalise(titre, contenueBytes, typeDeContenue, typeDePta);
                return ResponseEntity.ok(createPta);
            } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

}
