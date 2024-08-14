package finance.uc_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import finance.uc_project.model.Pta;

@Repository
public interface PtaRepository extends JpaRepository<Pta, Long> {
    
}
