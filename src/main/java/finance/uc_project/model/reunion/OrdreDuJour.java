package finance.uc_project.model.reunion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordre_du_jour")
public class OrdreDuJour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "info_reunion_base_id", nullable = false)
    private InfoReunionBase infoReunionBase;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InfoReunionBase getInfoReunionBase() {
        return infoReunionBase;
    }

    public void setInfoReunionBase(InfoReunionBase infoReunionBase) {
        this.infoReunionBase = infoReunionBase;
    }
}