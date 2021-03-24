package br.com.fef.campingclubapi.model;

import br.com.fef.campingclubapi.dto.EvaluationDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Evaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_evaluation;

    private int number;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "camping_id")
    private Camping camping;

    public Long getId_evaluation() {
        return id_evaluation;
    }

    public void setId_evaluation(Long id_evaluation) {
        this.id_evaluation = id_evaluation;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
        Evaluation that = (Evaluation) o;
        return Objects.equals(id_evaluation, that.id_evaluation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_evaluation);
    }

    public EvaluationDTO toEvaluation() {
        EvaluationDTO evaluationDTO = new EvaluationDTO();
        evaluationDTO.setId_evaluation(this.getId_evaluation());
        evaluationDTO.setNumber(this.getNumber());
        evaluationDTO.setId_camping(this.getCamping().getId_camping());
        evaluationDTO.setId_user(this.getUser().getId_user());
        return evaluationDTO;
    }
}
