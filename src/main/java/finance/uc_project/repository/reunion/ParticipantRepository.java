package finance.uc_project.repository.reunion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import finance.uc_project.model.reunion.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}