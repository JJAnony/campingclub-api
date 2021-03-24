package br.com.fef.campingclubapi.dto;

import br.com.fef.campingclubapi.model.Picture;
import br.com.fef.campingclubapi.model.Plant;
import br.com.fef.campingclubapi.model.Species;

import java.util.Base64;

public class PlantDTO {

    private Long id_plant;

    private String name;

    private int amount;

    private boolean poisonous;

    private boolean edible;

    private Long id_picture;

    private String picture;

    private Long id_species;

    private String species;

    public Long getId_plant() {
        return id_plant;
    }

    public void setId_plant(Long id_plant) {
        this.id_plant = id_plant;
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

    public boolean isPoisonous() {
        return poisonous;
    }

    public void setPoisonous(boolean poisonous) {
        this.poisonous = poisonous;
    }

    public boolean isEdible() {
        return edible;
    }

    public void setEdible(boolean edible) {
        this.edible = edible;
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

    public Plant toPlant() {
        Plant plant = new Plant();
        plant.setId_plant(this.getId_plant());
        plant.setName(this.getName());
        plant.setAmount(this.getAmount());
        plant.setEdible(this.isEdible());
        plant.setPoisonous(this.isPoisonous());

        Species species = new Species();
        species.setId_species(this.getId_species());
        species.setSpecies(this.getSpecies());
        plant.setSpecies(species);

        return plant;
    }

    public Picture toPicture() {
        Picture picture = new Picture();
        picture.setId_picture(this.getId_picture());
        picture.setMain(true);
        picture.setPicture(Base64.getDecoder().decode(this.getPicture()));
        return picture;
    }
}
