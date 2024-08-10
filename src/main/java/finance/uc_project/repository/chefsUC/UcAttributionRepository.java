package finance.uc_project.repository.chefsUC;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import finance.uc_project.model.chefsUC.UcAttribution;

@Repository
public interface UcAttributionRepository extends JpaRepository<UcAttribution, Long> {
}