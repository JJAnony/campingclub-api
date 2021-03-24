package br.com.fef.campingclubapi.dto;

import br.com.fef.campingclubapi.model.PhysicalPerson;
import br.com.fef.campingclubapi.model.User;

import javax.validation.constraints.NotNull;

public class AssociatedDTO {

    private Long id_associated;

    @NotNull(message = "Preencha o campo Nome!")
    private String name;

    @NotNull(message = "Preencha o campo Email!")
    private String username;

    @NotNull(message = "Preencha o campo Senha!")
    private String password;

    @NotNull(message = "Preencha os dados de pessoa Juridica!")
    private CampingDTO camping;


    public Long getId_associated() {
        return id_associated;
    }

    public void setId_associated(Long id_associated) {
        this.id_associated = id_associated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public CampingDTO getCamping() {
        return camping;
    }

    public void setCamping(CampingDTO camping) {
        this.camping = camping;
    }

    public User toUser() {
        User user = new User();
        user.setId_user(this.getId_associated());
        user.setUsername(this.getUsername());
        user.setPassword(this.getPassword());
        return user;
    }

    public PhysicalPerson toPhysicalPerson() {
        PhysicalPerson physicalPerson = new PhysicalPerson();
        physicalPerson.setName(this.getName());
        return physicalPerson;
    }
}
