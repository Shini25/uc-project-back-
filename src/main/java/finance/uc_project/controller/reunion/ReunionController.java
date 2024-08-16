package finance.uc_project.controller.reunion;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import finance.uc_project.model.reunion.InfoReunionBase;
import finance.uc_project.service.reunion.ReunionService;

@RestController
@RequestMapping("/api/reunions")
public class ReunionController {

    @Autowired
    private ReunionService reunionService;

    @GetMapping("/all")
    public ResponseEntity<List<InfoReunionBase>> getAllReunions() {
        return ResponseEntity.ok(reunionService.getAllReunions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoReunionBase> getReunionById(@PathVariable Long id) {
        return ResponseEntity.ok(reunionService.getReunionById(id));
    }

    @PostMapping("/planifier")
    public ResponseEntity<InfoReunionBase> planifierReunion(
            @RequestParam String titre,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateReunion,
            @RequestParam String lieu,
            @RequestParam String objet,
            @RequestParam List<String> ordreDuJourDescriptions,
            @RequestParam List<String> responsablesMatricules,
            @RequestParam List<String> participantsMatricules) {
        try {
            InfoReunionBase reunion = reunionService.planifierReunion(titre, dateReunion, lieu, objet, ordreDuJourDescriptions, responsablesMatricules, participantsMatricules);
            return new ResponseEntity<>(reunion, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    
}