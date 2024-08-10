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

import finance.uc_project.model.chefsUC.UcMotDuChef;
import finance.uc_project.service.chefsUC.UcMotDuChefService;

@RestController
@RequestMapping("/api/ucs")
public class UcMotDuChefController {

    @Autowired
    private UcMotDuChefService ucMotDuChefService;

    // CRUD pour UcMotDuChef
    @GetMapping("/{ucId}/motduchefs")
    public ResponseEntity<List<String>> getMotDuChefsByUc(@PathVariable String ucId) {
        return ResponseEntity.ok(ucMotDuChefService.getMotDuChefsByUc(ucId));
    }


    @PostMapping("/{ucId}/motduchefs")
    public ResponseEntity<UcMotDuChef> addMotDuChefToUc(@PathVariable String ucId, @RequestBody UcMotDuChef motDuChef) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ucMotDuChefService.addMotDuChefToUc(ucId, motDuChef));
    }

    @GetMapping("/motduchefs")
    public ResponseEntity<List<UcMotDuChef>> getAllMotDuChefs() {
        return ResponseEntity.ok(ucMotDuChefService.getAllMotDuChefs());
    }

    @GetMapping("/motduchefs/{id}")
    public ResponseEntity<UcMotDuChef> getMotDuChefById(@PathVariable Long id) {
        Optional<UcMotDuChef> motDuChef = ucMotDuChefService.getMotDuChefById(id);
        return motDuChef.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/motduchefs/{id}")
    public ResponseEntity<UcMotDuChef> updateMotDuChef(@PathVariable Long id, @RequestBody UcMotDuChef motDuChef) {
        motDuChef.setId(id);
        return ResponseEntity.ok(ucMotDuChefService.updateMotDuChef(motDuChef));
    }

    @DeleteMapping("/motduchefs/{id}")
    public ResponseEntity<Void> deleteMotDuChef(@PathVariable Long id) {
        ucMotDuChefService.deleteMotDuChef(id);
        return ResponseEntity.noContent().build();
    }
}