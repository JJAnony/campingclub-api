package br.com.fef.campingclubapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Species implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_species;

    private String species;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Species species = (Species) o;
        return Objects.equals(id_species, species.id_species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_species);
    }
}
