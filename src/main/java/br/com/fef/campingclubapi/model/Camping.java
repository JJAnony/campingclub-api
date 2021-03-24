package br.com.fef.campingclubapi.model;

import br.com.fef.campingclubapi.dto.CampingDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Camping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_camping;

    @Column(length = 1000)
    private String minimal_description;

    @Column(length = 5000)
    private String description;

    private double price;

    private int number_of_tents;

    private boolean bathroom;

    private boolean restaurant;

    private int square_meters;

    private boolean enable;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private LegalPerson person;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "addresses_campings",
            joinColumns = @JoinColumn(name = "camping_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addresses;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pictures_campings",
            joinColumns = @JoinColumn(name = "camping_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private List<Picture> pictures;

    @OneToMany(mappedBy = "camping", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "camping", fetch = FetchType.LAZY)
    private List<Evaluation> evaluations;

    @OneToMany(mappedBy = "camping", fetch = FetchType.LAZY)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "camping", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Fauna> faunas;

    @OneToMany(mappedBy = "camping", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Flora> floras;

    @OneToMany(mappedBy = "camping")
    private List<Employee> employees;


    public Camping() {
    }

    private Camping(Long id_camping, String description) {
        this.id_camping = id_camping;
        this.description = description;
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

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public LegalPerson getPerson() {
        return person;
    }

    public void setPerson(LegalPerson person) {
        this.person = person;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Fauna> getFaunas() {
        return faunas;
    }

    public void setFaunas(List<Fauna> faunas) {
        this.faunas = faunas;
    }

    public List<Flora> getFloras() {
        return floras;
    }

    public void setFloras(List<Flora> floras) {
        this.floras = floras;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camping camping = (Camping) o;
        return Objects.equals(id_camping, camping.id_camping);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_camping);
    }


    public CampingDTO toCamping() {
        CampingDTO campingDTO = new CampingDTO();

        campingDTO.setId_camping(this.getId_camping());
        campingDTO.setMinimal_description(this.getMinimal_description());
        campingDTO.setDescription(this.getDescription());
        campingDTO.setPrice(this.getPrice());
        campingDTO.setNumber_of_tents(this.getNumber_of_tents());
        campingDTO.setBathroom(this.isBathroom());
        campingDTO.setRestaurant(this.isRestaurant());
        campingDTO.setSquare_meters(this.getSquare_meters());

        campingDTO.setFantasy_name(this.getPerson().getName());
        campingDTO.setSocial_reason(this.getPerson().getSocial_reason());
        campingDTO.setCnpj(this.getPerson().getCnpj());
        campingDTO.setState_registration(this.getPerson().getState_registration());

        Address address = this.getAddresses().get(0);
        campingDTO.setState(address.getState());
        campingDTO.setCity(address.getCity());
        campingDTO.setZipcode(address.getZipcode());
        campingDTO.setLat(address.getCoordinates().getX());
        campingDTO.setLng(address.getCoordinates().getY());

        return campingDTO;
    }
}
