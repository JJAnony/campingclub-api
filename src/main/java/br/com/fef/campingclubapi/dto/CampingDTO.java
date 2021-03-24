package br.com.fef.campingclubapi.dto;

import br.com.fef.campingclubapi.model.Address;
import br.com.fef.campingclubapi.model.Camping;
import br.com.fef.campingclubapi.model.LegalPerson;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CampingDTO {

    private Long id_camping;

    private String minimal_description;

    private String description;

    private double price;

    private int number_of_tents;

    private boolean bathroom;

    private boolean restaurant;

    private int square_meters;

    @NotNull(message = "Preencha o campo Nome Fantasia!")
    private String fantasy_name;

    @NotNull(message = "Preencha o campo Inscrição Estadual!")
    private String state_registration;

    @NotNull(message = "Preencha o campo CNPJ!")
    private String cnpj;

    @NotNull(message = "Preencha o campo Razão Social!")
    private String social_reason;

    private String city;

    private String state;

    private String zipcode;

    private double lat;

    private double lng;

    private List<PictureDTO> pictures = new ArrayList<>();

    private List<CommentDTO> comments = new ArrayList<>();

    private List<FaunaDTO> faunas = new ArrayList<>();

    private List<FloraDTO> floras = new ArrayList<>();

    public CampingDTO() {
    }

    public Long getId_camping() {
        return id_camping;
    }

    public void setId_camping(Long id_camping) {
        this.id_camping = id_camping;
    }

    public String getMinimal_description() {
        return minimal_description;
    }

    public void setMinimal_description(String minimal_description) {
        this.minimal_description = minimal_description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber_of_tents() {
        return number_of_tents;
    }

    public void setNumber_of_tents(int number_of_tents) {
        this.number_of_tents = number_of_tents;
    }

    public boolean isBathroom() {
        return bathroom;
    }

    public void setBathroom(boolean bathroom) {
        this.bathroom = bathroom;
    }

    public boolean isRestaurant() {
        return restaurant;
    }

    public void setRestaurant(boolean restaurant) {
        this.restaurant = restaurant;
    }

    public int getSquare_meters() {
        return square_meters;
    }

    public void setSquare_meters(int square_meters) {
        this.square_meters = square_meters;
    }

    public String getFantasy_name() {
        return fantasy_name;
    }

    public void setFantasy_name(String fantasy_name) {
        this.fantasy_name = fantasy_name;
    }

    public String getState_registration() {
        return state_registration;
    }

    public void setState_registration(String state_registration) {
        this.state_registration = state_registration;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSocial_reason() {
        return social_reason;
    }

    public void setSocial_reason(String social_reason) {
        this.social_reason = social_reason;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public List<PictureDTO> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureDTO> pictures) {
        this.pictures = pictures;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public List<FaunaDTO> getFaunas() {
        return faunas;
    }

    public void setFaunas(List<FaunaDTO> faunas) {
        this.faunas = faunas;
    }

    public List<FloraDTO> getFloras() {
        return floras;
    }

    public void setFloras(List<FloraDTO> floras) {
        this.floras = floras;
    }

    public Camping toCamping() {
        Camping camping = new Camping();
        camping.setId_camping(this.getId_camping());
        camping.setMinimal_description(this.getMinimal_description());
        camping.setDescription(this.getDescription());
        camping.setPrice(this.getPrice());
        camping.setNumber_of_tents(this.getNumber_of_tents());
        camping.setBathroom(this.isBathroom());
        camping.setRestaurant(this.isRestaurant());
        camping.setSquare_meters(this.getSquare_meters());

        LegalPerson legalPerson = this.toLegalPerson();
        camping.setPerson(legalPerson);

        return camping;
    }

    public LegalPerson toLegalPerson() {
        LegalPerson legalPerson = new LegalPerson();
        legalPerson.setSocial_reason(this.getSocial_reason());
        legalPerson.setName(this.getFantasy_name());
        legalPerson.setState_registration(this.getState_registration());
        legalPerson.setCnpj(this.getCnpj());
        return legalPerson;
    }

    public Address toAddress() {
        Address address = new Address();
        address.setState(this.getState());
        address.setZipcode(this.getZipcode());
        address.setCity(this.getCity());
        address.setCoordinates(new GeometryFactory(new PrecisionModel(), 4326).createPoint(new Coordinate(this.getLat(), this.getLng())).getCentroid());
        return address;
    }

}
