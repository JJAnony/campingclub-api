package br.com.fef.campingclubapi.model;


import br.com.fef.campingclubapi.dto.ClientDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_client;

    @OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private PhysicalPerson person;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id_user")
    private User user;

    public Client() {
    }

    private Client(Long id_client) {
        this.id_client = id_client;
    }

    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

    public PhysicalPerson getPerson() {
        return person;
    }

    public void setPerson(PhysicalPerson person) {
        this.person = person;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id_client, client.id_client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_client);
    }

    public static Client toTransform(ClientDTO clientDTO) {
        return new Client(clientDTO.getId_cliente());
    }
}
