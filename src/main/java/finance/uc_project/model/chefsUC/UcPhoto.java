package finance.uc_project.model.chefsUC;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "uc_photos")
public class UcPhoto {

    @Id
    @SequenceGenerator(name = "uc_photo_sequence", sequenceName = "uc_photo_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uc_photo_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "uc_id")
    @JsonIgnore
    private Uc uc;

    @Column(name = "photo")
    private byte[] photo;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Uc getUc() {
        return uc;
    }

    public void setUc(Uc uc) {
        this.uc = uc;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}