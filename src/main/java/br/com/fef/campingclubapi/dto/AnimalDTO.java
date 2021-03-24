package br.com.fef.campingclubapi.dto;

import br.com.fef.campingclubapi.model.Animal;
import br.com.fef.campingclubapi.model.Picture;
import br.com.fef.campingclubapi.model.Species;

import java.util.Base64;

public class AnimalDTO {

    private Long id_animal;

    private String name;

    private int amount;

    private boolean extinction;

    private boolean hunting;

    private Long id_picture;

    private String picture;

    private Long id_species;

    private String species;

    public Long getId_animal() {
        return id_animal;
    }

    public void setId_animal(Long id_animal) {
        this.id_animal = id_animal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isExtinction() {
        return extinction;
    }

    public void setExtinction(boolean extinction) {
        this.extinction = extinction;
    }

    public boolean isHunting() {
        return hunting;
    }

    public void setHunting(boolean hunting) {
        this.hunting = hunting;
    }

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

    public Long getId_species() {
        return id_species;
    }

    public void setId_species(Long id_species) {
        this.id_species = id_species;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Animal toAnimal() {

        Animal animal = new Animal();
        animal.setId_animal(this.getId_animal());
        animal.setName(this.getName());
        animal.setAmount(this.getAmount());
        animal.setExtinction(this.isExtinction());
        animal.setHunting(this.isHunting());

        Species species = new Species();
        species.setId_species(this.getId_species());
        species.setSpecies(this.getSpecies());
        animal.setSpecies(species);

        return animal;
    }

    public Picture toPicture() {
        Picture picture = new Picture();
        picture.setId_picture(this.getId_picture());
        picture.setMain(true);
        picture.setPicture(Base64.getDecoder().decode(this.getPicture()));
        return picture;
    }

}
