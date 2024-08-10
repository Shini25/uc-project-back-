package finance.uc_project.model.chefsUC;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "uc")
public class Uc {

    @Id
    @Column(name = "matricule", nullable = false)
    private String matricule;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenoms", nullable = false)
    private String prenoms;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "contact", unique = true, nullable = false)
    private String contact;

    @OneToMany(mappedBy = "uc")
    @JsonIgnore
    private List<UcPhoto> photos;

    @OneToMany(mappedBy = "uc")
    @JsonIgnore
    private List<UcAttribution> attributions;

    @OneToMany(mappedBy = "uc")
    @JsonIgnore
    private List<UcMotDuChef> motDuChefs;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<UcPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<UcPhoto> photos) {
        this.photos = photos;
    }

    public List<UcAttribution> getAttributions() {
        return attributions;
    }

    public void setAttributions(List<UcAttribution> attributions) {
        this.attributions = attributions;
    }

    public List<UcMotDuChef> getMotDuChefs() {
        return motDuChefs;
    }

    public void setMotDuChefs(List<UcMotDuChef> motDuChefs) {
        this.motDuChefs = motDuChefs;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}