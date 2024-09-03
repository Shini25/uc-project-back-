package finance.uc_project.model.courriers;

import finance.uc_project.enums.courrier.PtaType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "pta")
public class Pta extends Courrier {

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PtaType type;

    @Column(name = "valide")
    private boolean valide;


    // Getters and Setters
    public PtaType getType() {
        return type;
    }

    public void setType(PtaType type) {
        this.type = type;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }


}
