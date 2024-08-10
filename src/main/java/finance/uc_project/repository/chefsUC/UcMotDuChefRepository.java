package finance.uc_project.repository.chefsUC;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import finance.uc_project.model.chefsUC.UcMotDuChef;

@Repository
public interface UcMotDuChefRepository extends JpaRepository<UcMotDuChef, Long> {
}
