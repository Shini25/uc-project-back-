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

import finance.uc_project.model.Courrier;
import finance.uc_project.service.CourrierService;

@RestController
@RequestMapping("/api/courriers")
public class CourrierController {

    @Autowired
    private CourrierService courrierService;

    @GetMapping
    public ResponseEntity<List<Courrier>> getAllCourriers() {
        List<Courrier> courriers = courrierService.getAllCourriers();
        return ResponseEntity.ok(courriers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Courrier> getCourrierById(@PathVariable Long id) {
        Optional<Courrier> courrier = courrierService.getCourrierById(id);
        return courrier.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/archivage")
    public ResponseEntity<Courrier> createCourrier(@RequestBody Courrier courrier) {
        Courrier createdCourrier = courrierService.createCourrier(courrier);
        return new ResponseEntity<>(createdCourrier, HttpStatus.CREATED);
    }

    @PostMapping("/archivage/personalise")
    public ResponseEntity<Courrier> createCourrierPersonalise(
                @RequestPart("titre") String titre,
                @RequestPart("contenue") String base64Contenue) {
            try {
                String base64pdf = base64Contenue.split(",")[1];
                byte[] pdfBytes = Base64.getDecoder().decode(base64pdf);
                Courrier createdCourrier = courrierService.createCourrierPersonalise(titre, pdfBytes);
                return ResponseEntity.ok(createdCourrier);
            } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Courrier> updateCourrier(@PathVariable Long id, @RequestBody Courrier courrier) {
        if (!courrierService.getCourrierById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        courrier.setId(id);
        Courrier updatedCourrier = courrierService.updateCourrier(courrier);
        return ResponseEntity.ok(updatedCourrier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourrier(@PathVariable Long id) {
        if (!courrierService.getCourrierById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        courrierService.deleteCourrier(id);
        return ResponseEntity.noContent().build();
    }
}
