package br.com.fef.campingclubapi.model;

import br.com.fef.campingclubapi.dto.CommentDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comment;

    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date_send;

    @ManyToOne
    @JoinColumn(name = "camping_id")
    private Camping camping;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId_comment() {
        return id_comment;
    }

    public void setId_comment(Long id_comment) {
        this.id_comment = id_comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate_send() {
        return date_send;
    }

    public void setDate_send(Date date_send) {
        this.date_send = date_send;
    }

    public Camping getCamping() {
        return camping;
    }

    public void setCamping(Camping camping) {
        this.camping = camping;
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
        Comment comment = (Comment) o;
        return Objects.equals(id_comment, comment.id_comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_comment);
    }

    public CommentDTO toComment() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId_comment(this.getId_comment());
        commentDTO.setComment(this.getComment());
        commentDTO.setDate_send(this.getDate_send());
        commentDTO.setId_camping(this.getCamping().getId_camping());
        commentDTO.setId_user(this.getUser().getId_user());
        if (this.getUser().getClient() != null) {
            commentDTO.setUser_name(this.getUser().getClient().getPerson().getName());
        } else {
            commentDTO.setUser_name(this.getUser().getEmployee().getPerson().getName());
        }
        return commentDTO;
    }
}
