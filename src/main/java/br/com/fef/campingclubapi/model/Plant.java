package br.com.fef.campingclubapi.model;


import br.com.fef.campingclubapi.dto.PlantDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Entity
public class Plant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_plant;

    private String name;

    private int amount;

    private boolean poisonous;

    private boolean edible;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "species_id")
    private Species species;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pictures_plants",
            joinColumns = @JoinColumn(name = "plant_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private List<Picture> pictures;

    @ManyToOne
    @JoinColumn(name = "flora_id")
    private Flora flora;

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

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Flora getFlora() {
        return flora;
    }

    public void setFlora(Flora flora) {
        this.flora = flora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return Objects.equals(id_plant, plant.id_plant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_plant);
    }

    public PlantDTO toPlant() {
        PlantDTO plantDTO = new PlantDTO();
        plantDTO.setId_plant(this.getId_plant());
        plantDTO.setName(this.getName());
        plantDTO.setAmount(this.getAmount());
        plantDTO.setPoisonous(this.isPoisonous());
        plantDTO.setEdible(this.isEdible());

        Picture picture = this.getPictures().get(0);
        plantDTO.setId_picture(picture.getId_picture());
        plantDTO.setPicture(Base64.getEncoder().encodeToString(picture.getPicture()));

        plantDTO.setId_species(this.getSpecies().getId_species());
        plantDTO.setSpecies(this.getSpecies().getSpecies());

        return plantDTO;
    }
}
