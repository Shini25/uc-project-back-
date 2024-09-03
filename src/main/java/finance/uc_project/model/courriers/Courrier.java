package finance.uc_project.model.courriers;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Courrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_courrier", updatable = false, nullable = false)
    private Long idCourrier;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "date_insertion", nullable = false, updatable = false)
    private LocalDateTime dateInsertion;

    @Column(name = "contenue")
    private byte[] contenue;

    @Column(name = "type_contenue")
    private String typeContenue;

    @PrePersist
    protected void onCreate() {
        dateInsertion = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getIdCourrier() {
        return idCourrier;
    }

    public void setIdCourrier(Long idCourrier) {
        this.idCourrier = idCourrier;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDateTime getDateInsertion() {
        return dateInsertion;
    }

    public void setDateInsertion(LocalDateTime dateInsertion) {
        this.dateInsertion = dateInsertion;
    }

    public byte[] getContenue() {
        return contenue;
    }

    public void setContenue(byte[] contenue) {
        this.contenue = contenue;
    }

    public String getTypeContenue() {
        return typeContenue;
    }

    public void setTypeContenue(String typeContenue) {
        this.typeContenue = typeContenue;
    }
}
