package finance.uc_project.controller.courriers;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import finance.uc_project.model.courriers.AutreDocument;
import finance.uc_project.service.courriers.AutreDocumentService;

@RestController
@RequestMapping("/api/autre-documents")
public class AutreDocumentController {

    @Autowired
    private AutreDocumentService autreDocumentService;

    @GetMapping
    public ResponseEntity<List<AutreDocument>> getAllAutreDocuments() {
        List<AutreDocument> autreDocuments = autreDocumentService.getAllAutreDocuments();
        return ResponseEntity.ok(autreDocuments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutreDocument> getAutreDocumentById(@PathVariable Long id) {
        Optional<AutreDocument> autreDocument = autreDocumentService.getAutreDocumentById(id);
        return autreDocument.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // @PostMapping("/insertion")
    // public ResponseEntity<AutreDocument> createLivret(@RequestBody AutreDocument autreDocument) {
    //     AutreDocument createdAutreDocument = autreDocumentService.createAutreDocument(autreDocument);
    //     return new ResponseEntity<>(createdAutreDocument, HttpStatus.CREATED);
    // }

    @PostMapping("/insertion/personalise")
        public ResponseEntity<AutreDocument> createLivretPersonalise(
                @RequestPart("titre") String titre,
                @RequestPart("contenue") String base64Contenue,
                @RequestPart("typeDeLivret") String typeDeLivret,
                @RequestPart("typeDeContenue") String typeDeContenue ) {
            try {

                String Base64Contenue = base64Contenue.split(",")[1];
                byte[] contenueBytes = Base64.getDecoder().decode(Base64Contenue);
                AutreDocument createAutreDocument = autreDocumentService.createAutreDocumentPersonalise(titre, contenueBytes, typeDeContenue, typeDeLivret);
                return ResponseEntity.ok(createAutreDocument);
            } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Livret> updateLivret(@PathVariable Long id, @RequestBody Livret pta) {
    //     if (!livretService.getLivretById(id).isPresent()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     pta.setId(id);
    //      updatedLivret = livretService.updateLivret(pta);
    //     return ResponseEntity.ok(updatedLivret);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteLivret(@PathVariable Long id) {
    //     if (!ptaService.getLivretById(id).isPresent()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     ptaService.deleteLivret(id);
    //     return ResponseEntity.noContent().build();
    // }
}
