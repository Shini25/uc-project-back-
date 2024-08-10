package finance.uc_project.controller.ChefsUC;

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
import org.springframework.web.bind.annotation.RestController;

import finance.uc_project.model.chefsUC.Uc;
import finance.uc_project.service.chefsUC.UcService;

@RestController
@RequestMapping("/api/uc")
public class UcController {

    @Autowired
    private UcService ucService;

    // CRUD pour Uc
    @GetMapping("/list")
    public ResponseEntity<List<Uc>> getAllUcs() {
        List<Uc> ucs = ucService.getAllUcs();
        return ResponseEntity.ok(ucs);
    }

    @GetMapping("/{matricule}")
    public ResponseEntity<Uc> getUcById(@PathVariable String matricule) {
        Optional<Uc> uc = ucService.getUcById(matricule);
        return uc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Uc> createUc(@RequestBody Uc uc) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ucService.createUc(uc));
    }

    @PutMapping("/{matricule}")
    public ResponseEntity<Uc> updateUc(@PathVariable String matricule, @RequestBody Uc uc) {
        uc.setMatricule(matricule);
        return ResponseEntity.ok(ucService.updateUc(uc));
    }

    @DeleteMapping("/{matricule}")
    public ResponseEntity<Void> deleteUc(@PathVariable String matricule) {
        ucService.deleteUc(matricule);
        return ResponseEntity.noContent().build();
    }
}
