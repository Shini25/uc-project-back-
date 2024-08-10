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

import finance.uc_project.model.chefsUC.UcAttribution;
import finance.uc_project.service.chefsUC.UcAttributionService;

@RestController
@RequestMapping("/api/ucs")
public class UcAttributionController {

    @Autowired
    private UcAttributionService ucAttributionService;

    // CRUD pour UcAttribution
    @GetMapping("/{ucId}/attributions")
    public ResponseEntity<List<String>> getAttributionsByUc(@PathVariable String ucId) {
        return ResponseEntity.ok(ucAttributionService.getAttributionsByUc(ucId));
    }

    @PostMapping("/{ucId}/attributions")
    public ResponseEntity<UcAttribution> addAttributionToUc(@PathVariable String ucId, @RequestBody UcAttribution attribution) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ucAttributionService.addAttributionToUc(ucId, attribution));
    }

    @GetMapping("/attributions")
    public ResponseEntity<List<UcAttribution>> getAllAttributions() {
        return ResponseEntity.ok(ucAttributionService.getAllAttributions());
    }

    @GetMapping("/attributions/{id}")
    public ResponseEntity<UcAttribution> getAttributionById(@PathVariable Long id) {
        Optional<UcAttribution> attribution = ucAttributionService.getAttributionById(id);
        return attribution.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/attributions/{id}")
    public ResponseEntity<UcAttribution> updateAttribution(@PathVariable Long id, @RequestBody UcAttribution attribution) {
        attribution.setId(id);
        return ResponseEntity.ok(ucAttributionService.updateAttribution(attribution));
    }

    @DeleteMapping("/attributions/{id}")
    public ResponseEntity<Void> deleteAttribution(@PathVariable Long id) {
        ucAttributionService.deleteAttribution(id);
        return ResponseEntity.noContent().build();
    }
}