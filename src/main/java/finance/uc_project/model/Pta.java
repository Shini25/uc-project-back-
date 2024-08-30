package finance.uc_project.model;

import java.time.LocalDateTime;

import finance.uc_project.enums.TypeDePta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pta")
public class Pta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "date_insertion", nullable = false, updatable = false)
    private LocalDateTime datePta;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeDePta", nullable = true)
    private TypeDePta typeDePta;

    @Column(name = "contenue")
    private byte[] contenue;

    @Column(name= "typeDeContenue")
    private String typeDeContenue;

    @PrePersist
    protected void onCreate() {
        datePta = LocalDateTime.now();
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

    public TypeDePta getTypeDePta() {
        return typeDePta;
    }

    public void setTypeDePta(TypeDePta typeDePta) {
        this.typeDePta = typeDePta;
    }

    public LocalDateTime getDatePta() {
        return datePta;
    }

    public void setDatePta(LocalDateTime datePta) {
        this.datePta = datePta;
    }

    public byte[] getContenue() {
        return contenue;
    }

    public void setContenue(byte[] contenue) {
        this.contenue = contenue;
    }

    public String getTypeDeContenue() {
        return typeDeContenue;
    }

    public void setTypeDeContenue(String typeDeContenue) {
        this.typeDeContenue = typeDeContenue;
    }
}