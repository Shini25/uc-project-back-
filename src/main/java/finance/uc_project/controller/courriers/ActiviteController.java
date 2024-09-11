package finance.uc_project.controller.courriers;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import finance.uc_project.model.courriers.Activite;
import finance.uc_project.service.courriers.ActiviteService;

@RestController
@RequestMapping("/api/activites")
public class ActiviteController {

    @Autowired
    private ActiviteService activiteService;


    @PostMapping("/insertion/personalise")
        public ResponseEntity<Activite> createActivitePersonalise(
                @RequestPart("titre") String titre,
                @RequestPart("contenue") String base64Contenue,
                @RequestPart("type") String type,
                @RequestPart("typeDeContenue") String typeDeContenue,
                @RequestPart("userId") String userId ) {
            try {
                String Base64Contenue = base64Contenue.split(",")[1];
                byte[] contenueBytes = Base64.getDecoder().decode(Base64Contenue);
                Activite createActivite = activiteService.createActivitePersonalise(titre, contenueBytes, typeDeContenue, type, userId);
                return ResponseEntity.ok(createActivite);
            } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<Activite>> getAllActivites() {
        List<Activite> activites = activiteService.getAllActivites();
        return ResponseEntity.ok(activites);
    }


}
