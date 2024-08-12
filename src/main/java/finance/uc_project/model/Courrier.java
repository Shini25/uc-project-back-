package finance.uc_project.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "courrier")
public class Courrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "date_insertion", nullable = false, updatable = false)
    private LocalDateTime dateCourrier;


    @Column(name = "contenue")
    private byte[] contenue;

    @PrePersist
    protected void onCreate() {
        dateCourrier = LocalDateTime.now();
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

    public LocalDateTime getDateCourrier() {
        return dateCourrier;
    }

    public void setDateCourrier(LocalDateTime dateCourrier) {
        this.dateCourrier = dateCourrier;
    }

    public byte[] getContenue() {
        return contenue;
    }

    public void setContenue(byte[] contenue) {
        this.contenue = contenue;
    }
}