package br.com.fef.campingclubapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id_person")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PhysicalPerson extends Person {

    @Column(length = 14)
    private String cpf;

    @Column(length = 12)
    private String rg;

    @Temporal(TemporalType.DATE)
    private Date birth_date;

    @Column(length = 1)
    private int genre;

    @OneToOne(optional = true, mappedBy = "person")
    private Client client;

    @OneToOne(optional = true, mappedBy = "person")
    private Employee employee;

    public PhysicalPerson() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
