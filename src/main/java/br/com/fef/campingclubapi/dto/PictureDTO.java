package br.com.fef.campingclubapi.dto;

import br.com.fef.campingclubapi.model.Picture;

import java.util.Base64;

public class PictureDTO {

    private Long id_picture;

    private String picture;

    private Boolean main;

    public Long getId_picture() {
        return id_picture;
    }

    public void setId_picture(Long id_picture) {
        this.id_picture = id_picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Picture toPicture() {
        Picture picture = new Picture();
        picture.setId_picture(this.getId_picture());
        picture.setPicture(Base64.getDecoder().decode(this.getPicture()));
        picture.setMain(this.getMain());
        return picture;
    }
}
