package finance.uc_project.service.courriers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finance.uc_project.enums.courrier.AccesReserveType;
import finance.uc_project.enums.courrier.TypeDocument;
import finance.uc_project.model.User_account;
import finance.uc_project.model.courriers.AccesReserve;
import finance.uc_project.repository.UserRepository;
import finance.uc_project.repository.courriers.AccesReserveRepository;

@Service
public class AccesReserveService {
    @Autowired
    private AccesReserveRepository accesReserveRepository;

    @Autowired
    private UserRepository userRepository;

    public AccesReserve createAccesReserve(String titre, byte[] contenue, String typeDeContenue, String accesReserveType, String userId) {
        AccesReserve accesReserve = new AccesReserve();
        accesReserve.setTitre(titre);
        accesReserve.setContenue(contenue);
        accesReserve.setDateInsertion(LocalDateTime.now());
        accesReserve.setType(AccesReserveType.valueOf(accesReserveType));
        accesReserve.setTypeContenue(typeDeContenue);
        accesReserve.setTypeDocument(TypeDocument.ACCES_RESERVE);
        User_account user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + userId));
        accesReserve.setUserId(user);
        return accesReserveRepository.save(accesReserve);
    }
}
