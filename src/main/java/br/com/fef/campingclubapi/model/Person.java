package br.com.fef.campingclubapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_person;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date_register;

    public Person() {
    }

    public Person(Long id_person, String name, Date date_register) {
        this.id_person = id_person;
        this.name = name;
        this.date_register = date_register;
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
}
