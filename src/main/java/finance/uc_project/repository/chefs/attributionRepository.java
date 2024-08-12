package finance.uc_project.repository.chefs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import finance.uc_project.model.chefs.attribution;

@Repository
public interface attributionRepository extends JpaRepository<attribution, Long> {
}