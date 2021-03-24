package br.com.fef.campingclubapi.service.v1;


import br.com.fef.campingclubapi.model.Camping;
import br.com.fef.campingclubapi.model.Evaluation;
import br.com.fef.campingclubapi.model.User;
import br.com.fef.campingclubapi.repository.CampingRepository;
import br.com.fef.campingclubapi.repository.EvaluationRepository;
import br.com.fef.campingclubapi.repository.UserRepository;
import br.com.fef.campingclubapi.dto.EvaluationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CampingRepository campingRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;


    public EvaluationDTO get(Long id_user, Long id_camping) throws Exception {
        Evaluation evaluation = evaluationRepository.findEvaluationByUserAndCamping(id_user, id_camping);
        return evaluation.toEvaluation();
    }

    public EvaluationDTO save(EvaluationDTO evaluationDTO) throws Exception {

        Evaluation evaluation = evaluationDTO.toEvaluation();

        Camping camping = campingRepository.findById(evaluationDTO.getId_camping())
                .orElseThrow(() -> new NullPointerException("Camping Não Encontrado!"));

        evaluation.setCamping(camping);

        User user = userRepository.findById(evaluationDTO.getId_user())
                .orElseThrow(() -> new NullPointerException("Usuario Não Encontrado!"));

        evaluation.setUser(user);

        evaluation = evaluationRepository.save(evaluation);

        return evaluation.toEvaluation();
    }


}
