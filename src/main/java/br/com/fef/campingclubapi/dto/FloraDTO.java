package br.com.fef.campingclubapi.dto;

import br.com.fef.campingclubapi.model.Flora;

import java.util.ArrayList;
import java.util.List;

public class FloraDTO {

    private Long id_flora;

    private int type;

    private int square_meters;

    private Long id_camping;

    private List<PlantDTO> plants = new ArrayList<>();

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

    public Long getId_camping() {
        return id_camping;
    }

    public void setId_camping(Long id_camping) {
        this.id_camping = id_camping;
    }

    public List<PlantDTO> getPlants() {
        return plants;
    }

    public void setPlants(List<PlantDTO> plants) {
        this.plants = plants;
    }

    public Flora toFlora() {
        Flora flora = new Flora();
        flora.setId_flora(this.getId_flora());
        flora.setSquare_meters(this.getSquare_meters());
        flora.setType(this.getType());
        return flora;
    }
}
