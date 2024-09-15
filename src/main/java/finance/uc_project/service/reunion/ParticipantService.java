package finance.uc_project.service.reunion;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import finance.uc_project.model.reunion.Participant;
import finance.uc_project.repository.reunion.InfoReunionBaseRepository;
import finance.uc_project.repository.reunion.ParticipantRepository;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private InfoReunionBaseRepository infoReunionBaseRepository;

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    @Transactional
    public List<String> getParticipantsByReunion(Long reunionId) {
        return infoReunionBaseRepository.findById(reunionId)
                .map(reunion -> reunion.getParticipants().stream()
                        .map(participant -> participant.getChef().getNumero())
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public Participant updateParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }
}