package finance.uc_project.model.reunion;

import finance.uc_project.model.chefs.infoBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "responsable_reunion")
public class ResponsableReunion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "info_reunion_base_id", nullable = false)
    private InfoReunionBase infoReunionBase;

    @ManyToOne
    @JoinColumn(name = "chef_matricule", nullable = false)
    private infoBase chef;

    @Column(name = "presence", nullable = false)
    private Boolean presence = false;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InfoReunionBase getInfoReunionBase() {
        return infoReunionBase;
    }

    public void setInfoReunionBase(InfoReunionBase infoReunionBase) {
        this.infoReunionBase = infoReunionBase;
    }

    public infoBase getChef() {
        return chef;
    }

    public void setChef(infoBase chef) {
        this.chef = chef;
    }

    public Boolean getPresence() {
        return presence;
    }

    public void setPresence(Boolean presence) {
        this.presence = presence;
    }
}