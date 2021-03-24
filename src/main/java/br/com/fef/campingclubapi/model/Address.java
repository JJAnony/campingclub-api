package br.com.fef.campingclubapi.model;

import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_address;

    private String city;

    private String state;

    private String zipcode;

    @Column(columnDefinition = "geometry(Point,4326)")
    public Point coordinates;


    public Address() {
    }

    private Address(Long id_address, String city, String state, String zipcode, Point coordinates) {
        this.id_address = id_address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.coordinates = coordinates;
    }

    public Long getId_address() {
        return id_address;
    }

    public void setId_address(Long id_address) {
        this.id_address = id_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

}
