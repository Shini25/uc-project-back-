package finance.uc_project.model.reunion;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "info_reunion_base")
public class InfoReunionBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre", nullable = false)
    private String titre;


    @Column(name = "date_insertion", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_reunion", nullable = false)
    private LocalDateTime dateReunion;

    @Column(name = "lieu", nullable = false)
    private String lieu;

    @Column(name = "objet", nullable = false)
    private String objet;

    @OneToMany(mappedBy = "infoReunionBase", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrdreDuJour> ordreDuJour;

    @OneToMany(mappedBy = "infoReunionBase", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ResponsableReunion> responsables;

    @OneToMany(mappedBy = "infoReunionBase", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Participant> participants;

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateReunion() {
        return dateReunion;
    }

    public void setDateReunion(LocalDateTime dateReunion) {
        this.dateReunion = dateReunion;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public List<OrdreDuJour> getOrdreDuJour() {
        return ordreDuJour;
    }

    public void setOrdreDuJour(List<OrdreDuJour> ordreDuJour) {
        this.ordreDuJour = ordreDuJour;
    }

    public List<ResponsableReunion> getResponsables() {
        return responsables;
    }

    public void setResponsables(List<ResponsableReunion> responsables) {
        this.responsables = responsables;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
}