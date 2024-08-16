package finance.uc_project.service.reunion;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import finance.uc_project.model.reunion.OrdreDuJour;
import finance.uc_project.repository.reunion.InfoReunionBaseRepository;
import finance.uc_project.repository.reunion.OrdreDuJourRepository;

@Service
public class OrdreDuJourService {

    @Autowired
    private OrdreDuJourRepository ordreDuJourRepository;

    @Autowired
    private InfoReunionBaseRepository infoReunionBaseRepository;

    public List<OrdreDuJour> getAllOrdreDuJours() {
        return ordreDuJourRepository.findAll();
    }

    public Optional<OrdreDuJour> getOrdreDuJourById(Long id) {
        return ordreDuJourRepository.findById(id);
    }

    @Transactional
    public List<String> getOrdreDuJourByReunion(Long reunionId) {
        return infoReunionBaseRepository.findById(reunionId)
                .<List<String>>map(infoReunionBase -> infoReunionBase.getOrdreDuJour().stream()
                        .map(OrdreDuJour::getDescription)
                        .collect(Collectors.toList()))
                .orElse(null);
    }
    
    

    public OrdreDuJour createOrdreDuJour(OrdreDuJour ordreDuJour) {
        return ordreDuJourRepository.save(ordreDuJour);
    }

    public OrdreDuJour updateOrdreDuJour(OrdreDuJour ordreDuJour) {
        return ordreDuJourRepository.save(ordreDuJour);
    }

    public void deleteOrdreDuJour(Long id) {
        ordreDuJourRepository.deleteById(id);
    }
}