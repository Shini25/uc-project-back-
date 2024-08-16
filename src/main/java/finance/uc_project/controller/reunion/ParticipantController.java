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

import finance.uc_project.model.reunion.Participant;
import finance.uc_project.service.reunion.ParticipantService;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping
    public ResponseEntity<List<Participant>> getAllParticipants() {
        List<Participant> participants = participantService.getAllParticipants();
        return ResponseEntity.ok(participants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id) {
        Optional<Participant> participant = participantService.getParticipantById(id);
        return participant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{participantId}/reunions")
    public ResponseEntity<List<String>> getParticipantByReunion(@PathVariable Long participantId) {
        List<String> reunions = participantService.getParticipantsByReunion(participantId);
        return ResponseEntity.ok(reunions);
    }

    @PostMapping
    public ResponseEntity<Participant> createParticipant(@RequestBody Participant participant) {
        Participant createdParticipant = participantService.createParticipant(participant);
        return new ResponseEntity<>(createdParticipant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable Long id, @RequestBody Participant participant) {
        if (!participantService.getParticipantById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        participant.setId(id);
        Participant updatedParticipant = participantService.updateParticipant(participant);
        return ResponseEntity.ok(updatedParticipant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        if (!participantService.getParticipantById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }
}