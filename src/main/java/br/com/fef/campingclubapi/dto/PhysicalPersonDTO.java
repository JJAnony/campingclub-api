package br.com.fef.campingclubapi.dto;

import java.util.Date;

public class PhysicalPersonDTO {

    private Long id_person;

    private String name;

    private Date date_register;

    private String cpf;

    private String rg;

    private Date birth_date;

    private String genre;

    public PhysicalPersonDTO() {
    }

    private PhysicalPersonDTO(Long id_person, String name, Date date_register, String cpf, String rg, Date birth_date, String genre) {
        this.id_person = id_person;
        this.name = name;
        this.date_register = date_register;
        this.cpf = cpf;
        this.rg = rg;
        this.birth_date = birth_date;
        this.genre = genre;
    }

    public Long getId_person() {
        return id_person;
    }

    public void setId_person(Long id_person) {
        this.id_person = id_person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_register() {
        return date_register;
    }

    public void setDate_register(Date date_register) {
        this.date_register = date_register;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
