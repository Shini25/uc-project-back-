package finance.uc_project.controller.reunion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finance.uc_project.model.reunion.ResponsableReunion;
import finance.uc_project.service.reunion.ResponsableReunionService;

@RestController
@RequestMapping("/api/responsables")
public class ResponsableReunionController {

    @Autowired
    private ResponsableReunionService responsableReunionService;

    @GetMapping
    public ResponseEntity<List<ResponsableReunion>> getAllResponsables() {
        List<ResponsableReunion> responsables = responsableReunionService.getAllResponsableReunions();
        return ResponseEntity.ok(responsables);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsableReunion> getResponsableById(@PathVariable Long id) {
        ResponsableReunion responsable = responsableReunionService.getResponsableReunionById(id);
        return responsable != null ? ResponseEntity.ok(responsable) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{reunionId}/responsables")
    public ResponseEntity<List<String>> getResponsableByReunion(@PathVariable Long reunionId) {
        List<String> responsables = responsableReunionService.getResponsableByReunion(reunionId);
        return ResponseEntity.ok(responsables);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponsableReunion> updateResponsable(@PathVariable Long id, @RequestBody ResponsableReunion responsable) {
        if (responsableReunionService.getResponsableReunionById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        responsable.setId(id);
        ResponsableReunion updatedResponsable = responsableReunionService.updateResponsableReunion(responsable);
        return ResponseEntity.ok(updatedResponsable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponsable(@PathVariable Long id) {
        if (responsableReunionService.getResponsableReunionById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        responsableReunionService.deleteResponsableReunion(id);
        return ResponseEntity.noContent().build();
    }
}