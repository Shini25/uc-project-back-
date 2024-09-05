package finance.uc_project.controller.courriers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import finance.uc_project.model.courriers.Livret;
import finance.uc_project.service.courriers.LivretService;

@RestController
@RequestMapping("/api/livrets")
public class LivretController {

    @Autowired
    private LivretService livretService;

    @PostMapping("/insertion/personalise")
        public ResponseEntity<Livret> createLivretPersonalise(
                @RequestPart("titre") String titre,
                @RequestPart("contenue") String base64Contenue,
                @RequestPart("type") String type,
                @RequestPart("typeDeContenue") String typeDeContenue,
                @RequestPart("userId") String userId) {
            try {

                String Base64Contenue = base64Contenue.split(",")[1];
                byte[] contenueBytes = Base64.getDecoder().decode(Base64Contenue);
                Livret createLivret = livretService.createLivretPersonalise(titre, contenueBytes, typeDeContenue, type, userId);
                return ResponseEntity.ok(createLivret);
            } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

}
