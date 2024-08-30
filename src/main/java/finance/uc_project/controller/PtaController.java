package finance.uc_project.controller;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import finance.uc_project.model.Pta;
import finance.uc_project.service.PtaService;

@RestController
@RequestMapping("/api/ptas")
public class PtaController {

    @Autowired
    private PtaService ptaService;

    @GetMapping
    public ResponseEntity<List<Pta>> getAllPta() {
        List<Pta> ptas = ptaService.getAllPta();
        return ResponseEntity.ok(ptas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pta> getPtaById(@PathVariable Long id) {
        Optional<Pta> pta = ptaService.getPtaById(id);
        return pta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/insertion")
    public ResponseEntity<Pta> createPta(@RequestBody Pta pta) {
        Pta createdPta = ptaService.createPta(pta);
        return new ResponseEntity<>(createdPta, HttpStatus.CREATED);
    }

    @PostMapping("/insertion/personalise")
        public ResponseEntity<Pta> createPtaPersonalise(
                @RequestPart("titre") String titre,
                @RequestPart("contenue") String base64Contenue,
                @RequestPart("typeDePta") String typeDePta,
                @RequestPart("typeDeContenue") String typeDeContenue ) {
            try {

                String base64pdf = base64Contenue.split(",")[1];
                byte[] pdfBytes = Base64.getDecoder().decode(base64pdf);
                Pta createdPta = ptaService.createPtaPersonalise(titre, pdfBytes, typeDeContenue, typeDePta);
                return ResponseEntity.ok(createdPta);
            } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pta> updatePta(@PathVariable Long id, @RequestBody Pta pta) {
        if (!ptaService.getPtaById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pta.setId(id);
        Pta updatedPta = ptaService.updatePta(pta);
        return ResponseEntity.ok(updatedPta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePta(@PathVariable Long id) {
        if (!ptaService.getPtaById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ptaService.deletePta(id);
        return ResponseEntity.noContent().build();
    }
}
