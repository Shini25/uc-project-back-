package finance.uc_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import finance.uc_project.model.User_account;

@Repository
public interface UserRepository extends JpaRepository<User_account, String> {
    Optional<User_account> findByMatricule(String matricule);
    
}
