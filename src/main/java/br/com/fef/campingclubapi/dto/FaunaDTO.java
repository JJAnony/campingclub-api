package br.com.fef.campingclubapi.dto;

import br.com.fef.campingclubapi.model.Fauna;

import java.util.ArrayList;
import java.util.List;

public class FaunaDTO {

    private Long id_fauna;

    private int type;

    private int square_meters;

    private Long id_camping;

    private List<AnimalDTO> animals = new ArrayList<>();

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

    public Long getId_camping() {
        return id_camping;
    }

    public void setId_camping(Long id_camping) {
        this.id_camping = id_camping;
    }

    public List<AnimalDTO> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalDTO> animals) {
        this.animals = animals;
    }

    public Fauna toFauna(){
        Fauna fauna = new Fauna();
        fauna.setId_fauna(this.getId_fauna());
        fauna.setType(this.getType());
        fauna.setSquare_meters(this.getSquare_meters());
        return fauna;
    }

}
