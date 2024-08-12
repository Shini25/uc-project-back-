package finance.uc_project.controller.Chefs;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import finance.uc_project.model.chefs.infoBase;
import finance.uc_project.service.chefs.creationChefService;

@RestController
@RequestMapping("/api/chefs")
public class creationChefController {

    @Autowired
    private creationChefService chefCreationService;

    @PostMapping("/create")
    public ResponseEntity<infoBase> createChefs(
            @RequestPart("chef") infoBase chef,
            @RequestPart("photo") String base64Photo,
            @RequestPart("attributions") List<String> attributions,
            @RequestPart("motsDuChef") List<String> motsDuChef) {
        try {
            String base64Image = base64Photo.split(",")[1];
            byte[] photoBytes = Base64.getDecoder().decode(base64Image);
            infoBase createdChef = chefCreationService.createChef(chef, photoBytes, attributions, motsDuChef);
            return ResponseEntity.ok(createdChef);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}