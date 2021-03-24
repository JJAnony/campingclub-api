package br.com.fef.campingclubapi.model;

import br.com.fef.campingclubapi.dto.PictureDTO;

import javax.persistence.*;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_picture;

    private byte[] picture;

    private Boolean main;

    @ManyToMany(mappedBy = "pictures")
    private List<Camping> campings;

    @ManyToMany(mappedBy = "pictures")
    private List<Animal> animals;

    @ManyToMany(mappedBy = "pictures")
    private List<Plant> plants;

    public Long getId_picture() {
        return id_picture;
    }

    public void setId_picture(Long id_picture) {
        this.id_picture = id_picture;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public List<Camping> getCampings() {
        return campings;
    }

    public void setCampings(List<Camping> campings) {
        this.campings = campings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture pictures = (Picture) o;
        return Objects.equals(id_picture, pictures.id_picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_picture);
    }

    public PictureDTO toPicture() {
        PictureDTO pictureDTO = new PictureDTO();
        pictureDTO.setId_picture(this.getId_picture());
        pictureDTO.setPicture(Base64.getEncoder().encodeToString(this.getPicture()));
        pictureDTO.setMain(this.getMain());
        return pictureDTO;
    }


}
