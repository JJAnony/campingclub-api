package br.com.fef.campingclubapi.model;

import br.com.fef.campingclubapi.dto.FaunaDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Fauna implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_fauna;

    private int type;

    private int square_meters;

    @ManyToOne
    @JoinColumn(name = "camping_id")
    private Camping camping;

    @OneToMany(mappedBy = "fauna", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Animal> animals;

    public Long getId_fauna() {
        return id_fauna;
    }

    public void setId_fauna(Long id_fauna) {
        this.id_fauna = id_fauna;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSquare_meters() {
        return square_meters;
    }

    public void setSquare_meters(int square_meters) {
        this.square_meters = square_meters;
    }

    public Camping getCamping() {
        return camping;
    }

    public void setCamping(Camping camping) {
        this.camping = camping;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fauna fauna = (Fauna) o;
        return Objects.equals(id_fauna, fauna.id_fauna);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_fauna);
    }

    public FaunaDTO toFauna() {
        FaunaDTO faunaDTO = new FaunaDTO();
        faunaDTO.setId_fauna(this.getId_fauna());
        faunaDTO.setSquare_meters(this.getSquare_meters());
        faunaDTO.setType(this.getType());
        faunaDTO.setId_camping(this.getCamping().getId_camping());
        return faunaDTO;
    }
}
