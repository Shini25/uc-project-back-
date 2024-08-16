package finance.uc_project.repository.reunion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import finance.uc_project.model.reunion.ResponsableReunion;

@Repository
public interface ResponsableReunionRepository extends JpaRepository<ResponsableReunion, Long> {
}