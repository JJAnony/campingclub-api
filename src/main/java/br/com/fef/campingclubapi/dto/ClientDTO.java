package br.com.fef.campingclubapi.dto;


import br.com.fef.campingclubapi.model.PhysicalPerson;
import br.com.fef.campingclubapi.model.User;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ClientDTO {

    private Long id_cliente;

    @NotNull(message = "Preencha o campo Nome!")
    private String name;

    private Date date_register;

    private String cpf;

    private String rg;

    private Date birth_date;

    private int genre;

    @NotNull(message = "Preencha o campo Email!")
    private String username;

    @NotNull(message = "Preencha o campo Senha!")
    private String password;


    public ClientDTO() {
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
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

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User toUser() {
        User user = new User();
        user.setId_user(this.getId_cliente());
        user.setUsername(this.getUsername());
        user.setPassword(this.getPassword());
        return user;
    }

    public PhysicalPerson toPhysicalPerson() {
        PhysicalPerson physicalPerson = new PhysicalPerson();
        physicalPerson.setName(this.getName());
        physicalPerson.setDate_register(this.getDate_register());
        physicalPerson.setBirth_date(this.getBirth_date());
        physicalPerson.setGenre(this.getGenre());
        physicalPerson.setCpf(this.getCpf());
        physicalPerson.setRg(this.getRg());
        return physicalPerson;
    }
}
