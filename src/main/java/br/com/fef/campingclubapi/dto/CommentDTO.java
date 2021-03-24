package br.com.fef.campingclubapi.dto;

import br.com.fef.campingclubapi.model.Comment;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CommentDTO {

    private Long id_comment;

    @NotNull(message = "Digite Um comentario!")
    @NotEmpty(message = "Digite Um comentario!")
    private String comment;

    private Date date_send;

    @NotNull(message = "Camping Não pode ser Nullo!")
    @NotEmpty(message = "Camping Não pode ser Vazio!")
    private Long id_camping;

    @NotNull(message = "Usuario Não pode ser Nullo!")
    @NotEmpty(message = "Usuario Não pode ser Vazio!")
    private Long id_user;

    private String user_name;

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

    public Long getId_camping() {
        return id_camping;
    }

    public void setId_camping(Long id_camping) {
        this.id_camping = id_camping;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Comment toComment() {
        Comment comment = new Comment();
        comment.setId_comment(this.getId_comment());
        comment.setComment(this.getComment());
        return comment;
    }
}
