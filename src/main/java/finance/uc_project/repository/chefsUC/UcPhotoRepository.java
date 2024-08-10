package finance.uc_project.repository.chefsUC;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import finance.uc_project.model.chefsUC.UcPhoto;

@Repository
public interface UcPhotoRepository extends JpaRepository<UcPhoto, Long> {
}
