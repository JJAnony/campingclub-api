package br.com.fef.campingclubapi.model;

import br.com.fef.campingclubapi.dto.FloraDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Flora implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_flora;

    private int type;

    private int square_meters;

    @ManyToOne
    @JoinColumn(name = "camping_id")
    private Camping camping;

    @OneToMany(mappedBy = "flora", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Plant> plants;


    public Long getId_flora() {
        return id_flora;
    }

    public void setId_flora(Long id_flora) {
        this.id_flora = id_flora;
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

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flora flora = (Flora) o;
        return Objects.equals(id_flora, flora.id_flora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_flora);
    }

    public FloraDTO toFlora() {
        FloraDTO floraDTO = new FloraDTO();
        floraDTO.setId_flora(this.getId_flora());
        floraDTO.setType(this.getType());
        floraDTO.setSquare_meters(this.getSquare_meters());
        floraDTO.setId_camping(this.getCamping().getId_camping());
        return floraDTO;
    }
}
