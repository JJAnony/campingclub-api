package br.com.fef.campingclubapi.dto;

import br.com.fef.campingclubapi.model.LegalPerson;

import java.util.Date;

public class LegalPersonDTO {

    private Long id_person;

    private Date date_register;

    private String state_registration;

    private String cnpj;

    private String social_reason;

    private String fantasy_name;

    public LegalPersonDTO() {
    }

    private LegalPersonDTO(Long id_person, Date date_register, String state_registration, String cnpj, String social_reason, String fantasy_name) {
        this.id_person = id_person;
        this.date_register = date_register;
        this.state_registration = state_registration;
        this.cnpj = cnpj;
        this.social_reason = social_reason;
        this.fantasy_name = fantasy_name;
    }

    public Long getId_person() {
        return id_person;
    }

    public void setId_person(Long id_person) {
        this.id_person = id_person;
    }

    public Date getDate_register() {
        return date_register;
    }

    public void setDate_register(Date date_register) {
        this.date_register = date_register;
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

    public String getFantasy_name() {
        return fantasy_name;
    }

    public void setFantasy_name(String fantasy_name) {
        this.fantasy_name = fantasy_name;
    }

    public static LegalPersonDTO transformInVO(LegalPerson legalPerson) {
        return new LegalPersonDTO(legalPerson.getId_person(),
                legalPerson.getDate_register(), legalPerson.getState_registration(), legalPerson.getCnpj(),
                legalPerson.getSocial_reason(), legalPerson.getName());
    }
}
