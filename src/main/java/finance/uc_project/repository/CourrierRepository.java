package finance.uc_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import finance.uc_project.model.Courrier;

@Repository
public interface CourrierRepository extends JpaRepository<Courrier, Long> {
}
