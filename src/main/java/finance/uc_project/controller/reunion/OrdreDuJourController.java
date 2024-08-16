package finance.uc_project.controller.reunion;

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

import finance.uc_project.model.reunion.OrdreDuJour;
import finance.uc_project.service.reunion.OrdreDuJourService;

@RestController
@RequestMapping("/api/ordredujour")
public class OrdreDuJourController {

    @Autowired
    private OrdreDuJourService ordreDuJourService;

    @GetMapping
    public ResponseEntity<List<OrdreDuJour>> getAllOrdreDuJours() {
        List<OrdreDuJour> ordreDuJours = ordreDuJourService.getAllOrdreDuJours();
        return ResponseEntity.ok(ordreDuJours);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdreDuJour> getOrdreDuJourById(@PathVariable Long id) {
        Optional<OrdreDuJour> ordreDuJour = ordreDuJourService.getOrdreDuJourById(id);
        return ordreDuJour.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{reunionId}/ordredujour")
    public ResponseEntity<List<String>> getOrdreDuJourByReunion(@PathVariable Long reunionId) {
        List<String> ordreDuJour = ordreDuJourService.getOrdreDuJourByReunion(reunionId);
        return ResponseEntity.ok(ordreDuJour);
    }

    @PostMapping
    public ResponseEntity<OrdreDuJour> createOrdreDuJour(@RequestBody OrdreDuJour ordreDuJour) {
        OrdreDuJour createdOrdreDuJour = ordreDuJourService.createOrdreDuJour(ordreDuJour);
        return new ResponseEntity<>(createdOrdreDuJour, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdreDuJour> updateOrdreDuJour(@PathVariable Long id, @RequestBody OrdreDuJour ordreDuJour) {
        if (!ordreDuJourService.getOrdreDuJourById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ordreDuJour.setId(id);
        OrdreDuJour updatedOrdreDuJour = ordreDuJourService.updateOrdreDuJour(ordreDuJour);
        return ResponseEntity.ok(updatedOrdreDuJour);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdreDuJour(@PathVariable Long id) {
        if (!ordreDuJourService.getOrdreDuJourById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ordreDuJourService.deleteOrdreDuJour(id);
        return ResponseEntity.noContent().build();
    }
}