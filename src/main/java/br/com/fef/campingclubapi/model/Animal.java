package br.com.fef.campingclubapi.model;

import br.com.fef.campingclubapi.dto.AnimalDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Entity
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_animal;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "species_id")
    private Species species;

    private int amount;

    private boolean extinction;

    private boolean hunting;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pictures_animals",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private List<Picture> pictures;

    @ManyToOne
    @JoinColumn(name = "fauna_id")
    private Fauna fauna;

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

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
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

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Fauna getFauna() {
        return fauna;
    }

    public void setFauna(Fauna fauna) {
        this.fauna = fauna;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id_animal, animal.id_animal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_animal);
    }

    public AnimalDTO toAnimal() {
        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setId_animal(this.getId_animal());
        animalDTO.setName(this.getName());
        animalDTO.setAmount(this.getAmount());
        animalDTO.setExtinction(this.isExtinction());
        animalDTO.setHunting(this.isHunting());

        Picture picture = this.getPictures().get(0);
        animalDTO.setId_picture(picture.getId_picture());
        animalDTO.setPicture(Base64.getEncoder().encodeToString(picture.getPicture()));

        animalDTO.setId_species(this.getSpecies().getId_species());
        animalDTO.setSpecies(this.getSpecies().getSpecies());

        return animalDTO;
    }
}
