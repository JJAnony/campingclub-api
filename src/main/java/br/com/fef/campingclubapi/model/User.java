package br.com.fef.campingclubapi.model;

import br.com.fef.campingclubapi.dto.AssociatedDTO;
import br.com.fef.campingclubapi.dto.ClientDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @NotNull(message = "Username Não Pode Ser Nulo!")
    @NotEmpty(message = "Username Não Pode Ser Vazio!")
    @Column(unique = true)
    private String username;

    @NotNull(message = "Password Não Pode Ser Nulo!")
    @NotEmpty(message = "Password Não Pode Ser Vazio!")
    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Employee employee;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Client client;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Evaluation> evaluations;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Favorite> favorites;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            uniqueConstraints = @UniqueConstraint(name = "unique_role_user", columnNames = {"user_id", "role_id"}),
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_user", table = "users",
                    foreignKey = @ForeignKey(name = "user_fk", value = ConstraintMode.CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id_role", table = "role",
                    foreignKey = @ForeignKey(name = "role_fk", value = ConstraintMode.CONSTRAINT)))
    private List<Role> roles;


    public User() {
    }

    private User(Long id_user, String username, String password) {
        this.id_user = id_user;
        this.username = username;
        this.password = password;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * @return Se o prazo da conta foi expirado
     */
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    /**
     * @return Se a conta esta Trancada ou Bloqueada
     */
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    /**
     * @return Se as Credenciais da conta foram Expiradas
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    /**
     * @return Se a conta esta ativa
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return As autorizações do Usuario
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id_user, user.id_user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_user);
    }


    public AssociatedDTO toAssociated() {
        AssociatedDTO associatedDTO = new AssociatedDTO();
        associatedDTO.setId_associated(this.getId_user());
        associatedDTO.setUsername(this.getUsername());
        associatedDTO.setPassword(this.getPassword());
        associatedDTO.setName(this.getEmployee().getPerson().getName());
        associatedDTO.setCamping(this.getEmployee().getCamping().toCamping());
        return associatedDTO;
    }

    public ClientDTO toClient() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId_cliente(this.getId_user());
        clientDTO.setUsername(this.getUsername());
        clientDTO.setPassword(this.getPassword());
        clientDTO.setName(this.getClient().getPerson().getName());
        clientDTO.setDate_register(this.getClient().getPerson().getDate_register());
        clientDTO.setRg(this.getClient().getPerson().getRg());
        clientDTO.setCpf(this.getClient().getPerson().getCpf());
        clientDTO.setGenre(this.getClient().getPerson().getGenre());
        return clientDTO;
    }

}
