package br.com.fef.campingclubapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_favorite;

    private boolean favorite;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "camping_id")
    private Camping camping;


    public Long getId_favorite() {
        return id_favorite;
    }

    public void setId_favorite(Long id_favorite) {
        this.id_favorite = id_favorite;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Camping getCamping() {
        return camping;
    }

    public void setCamping(Camping camping) {
        this.camping = camping;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return Objects.equals(id_favorite, favorite.id_favorite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_favorite);
    }
}
