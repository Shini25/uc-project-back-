package finance.uc_project.controller.ChefsUC;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import finance.uc_project.model.chefsUC.Uc;
import finance.uc_project.service.chefsUC.UcCreationService;

@RestController
@RequestMapping("/api/uc")
public class UcCreationController {

    @Autowired
    private UcCreationService ucCreationService;

    @PostMapping("/create")
    public ResponseEntity<Uc> createUc(
            @RequestPart("uc") Uc uc,
            @RequestPart("photo") String base64Photo,
            @RequestPart("attributions") List<String> attributions,
            @RequestPart("motsDuChef") List<String> motsDuChef) {
        try {
            String base64Image = base64Photo.split(",")[1];
            byte[] photoBytes = Base64.getDecoder().decode(base64Image);
            Uc createdUc = ucCreationService.createUc(uc, photoBytes, attributions, motsDuChef);
            return ResponseEntity.ok(createdUc);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null); // Bad Request if base64 decoding fails
        }
    }
}