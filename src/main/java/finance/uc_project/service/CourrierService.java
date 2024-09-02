// package finance.uc_project.service;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import finance.uc_project.model.courriers.Courrier;
// import finance.uc_project.repository.CourrierRepository;

// @Service
// public class CourrierService {

//     @Autowired
//     private CourrierRepository courrierRepository;

//     public List<Courrier> getAllCourriers() {
//         return courrierRepository.findAll();
//     }

//     public Optional<Courrier> getCourrierById(Long id) {
//         return courrierRepository.findById(id);
//     }

//     public Courrier createCourrier(Courrier courrier) {
//         return courrierRepository.save(courrier);
//     }

//     public Courrier createCourrierPersonalise(String titre, byte[] contenue) {

//         Courrier courrier = new Courrier();
//         courrier.setTitre(titre);
//         courrier.setContenue(contenue);
//         courrier.setDateCourrier(LocalDateTime.now());
//         return courrierRepository.save(courrier);
//     }

//     public Courrier updateCourrier(Courrier courrier) {
//         return courrierRepository.save(courrier);
//     }

//     public void deleteCourrier(Long id) {
//         courrierRepository.deleteById(id);
//     }
// }
