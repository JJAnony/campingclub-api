package br.com.fef.campingclubapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id_person")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LegalPerson extends Person {

    private String state_registration;

    private String cnpj;

    private String social_reason;

    @OneToOne(mappedBy = "person")
    private Camping camping;

    public LegalPerson() {
    }

    private LegalPerson(Long id_person, String name, Date date_register, String state_registration, String cnpj, String social_reason) {
        super(id_person, name, date_register);
        this.state_registration = state_registration;
        this.cnpj = cnpj;
        this.social_reason = social_reason;
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

    public Camping getCamping() {
        return camping;
    }

    public void setCamping(Camping camping) {
        this.camping = camping;
    }

}
