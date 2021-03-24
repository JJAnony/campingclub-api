package br.com.fef.campingclubapi.dto;

import br.com.fef.campingclubapi.model.Evaluation;

public class EvaluationDTO {

    private Long id_evaluation;

    private int number;

    private long id_camping;

    private long id_user;

    public EvaluationDTO() {
    }

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

    public long getId_camping() {
        return id_camping;
    }

    public void setId_camping(long id_camping) {
        this.id_camping = id_camping;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public Evaluation toEvaluation() {
        Evaluation evaluation = new Evaluation();
        evaluation.setId_evaluation(this.getId_evaluation());
        evaluation.setNumber(this.getNumber());
        return evaluation;
    }


}
