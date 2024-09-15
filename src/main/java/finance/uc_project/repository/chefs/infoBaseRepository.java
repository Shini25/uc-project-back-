package finance.uc_project.repository.chefs;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import finance.uc_project.enums.TypeDeChef;
import finance.uc_project.model.chefs.infoBase;

@Repository
public interface infoBaseRepository extends JpaRepository<infoBase, String> {
    Optional<infoBase> findByNumero(String numero);
    
    List<infoBase> findByTypeDeChef(TypeDeChef typeDeChef);
}