package finance.uc_project.repository.chefsUC;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import finance.uc_project.model.chefsUC.Uc;

@Repository
public interface UcRepository extends JpaRepository<Uc, String> {
        Optional<Uc> findByMatricule(String matricule);
}