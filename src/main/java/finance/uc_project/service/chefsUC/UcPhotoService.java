package finance.uc_project.service.chefsUC;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finance.uc_project.model.chefsUC.UcPhoto;
import finance.uc_project.repository.chefsUC.UcPhotoRepository;
import finance.uc_project.repository.chefsUC.UcRepository;

@Service
public class UcPhotoService {

    @Autowired
    private UcPhotoRepository ucPhotoRepository;

    @Autowired
    private UcRepository ucRepository;

    public List<UcPhoto> getAllPhotos() {
        return ucPhotoRepository.findAll();
    }

    public Optional<UcPhoto> getPhotoById(Long id) {
        return ucPhotoRepository.findById(id);
    }

    public UcPhoto createPhoto(UcPhoto photo) {
        return ucPhotoRepository.save(photo);
    }

    public UcPhoto updatePhoto(UcPhoto photo) {
        return ucPhotoRepository.save(photo);
    }

    public void deletePhoto(Long id) {
        ucPhotoRepository.deleteById(id);
    }

    public List<String> getPhotosByUc(String ucId) {
        return ucRepository.findById(ucId)
                .map(uc -> uc.getPhotos().stream()
                        .map(photo -> Base64.getEncoder().encodeToString(photo.getPhoto()))
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public UcPhoto addPhotoToUc(String ucId, UcPhoto photo) {
        return ucRepository.findById(ucId).map(uc -> {
            photo.setUc(uc);
            return ucPhotoRepository.save(photo);
        }).orElse(null);
    }

    public String getPhotoBase64ById(Long id) {
        return ucPhotoRepository.findById(id)
                .map(photo -> Base64.getEncoder().encodeToString(photo.getPhoto()))
                .orElse(null);
    }

    public List<String> getAllPhotosBase64() {
        return ucPhotoRepository.findAll().stream()
                .map(photo -> Base64.getEncoder().encodeToString(photo.getPhoto()))
                .collect(Collectors.toList());
    }
}