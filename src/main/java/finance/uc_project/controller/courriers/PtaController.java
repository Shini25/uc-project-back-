package finance.uc_project.controller.courriers;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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


    @PostMapping("/insertion/personalise")
        public ResponseEntity<Pta> createPtaPersonalise(
                @RequestPart("titre") String titre,
                @RequestPart("contenue") String base64Contenue,
                @RequestPart("type") String type,
                @RequestPart("typeDeContenue") String typeDeContenue,
                @RequestPart("userId") String userId ) {
            try {

                String Base64Contenue = base64Contenue.split(",")[1];
                byte[] contenueBytes = Base64.getDecoder().decode(Base64Contenue);
                Pta createPta = ptaService.createPtaPersonalise(titre, contenueBytes, typeDeContenue, type, userId);
                return ResponseEntity.ok(createPta);
            } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pta>> getAllPta() {
        List<Pta> ptas = ptaService.getAllPta();
        return ResponseEntity.ok(ptas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pta> updatePta(@PathVariable Long id, @RequestPart("contenue") String base64Contenue) {
        try {

            String Base64Contenue = base64Contenue.split(",")[1];
            byte[] contenueBytes = Base64.getDecoder().decode(Base64Contenue);
            Pta updatedPta = ptaService.updatePta(id, contenueBytes);
            return ResponseEntity.ok(updatedPta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/valider/{id}")
    public ResponseEntity<Pta> validerPta(@PathVariable Long id) {
        Pta validerPta = ptaService.validerPta(id);
        return ResponseEntity.ok(validerPta);
    }

}
