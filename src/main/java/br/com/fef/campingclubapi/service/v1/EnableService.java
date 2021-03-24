package br.com.fef.campingclubapi.service.v1;

import br.com.fef.campingclubapi.enums.EMessage;
import br.com.fef.campingclubapi.model.User;
import br.com.fef.campingclubapi.repository.UserRepository;
import br.com.fef.campingclubapi.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnableService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AESService aesService;

    public MessageDTO active(String code) throws Exception {
        String username = aesService.decrypted(code);
        User user = userRepository.findByUsername(username);
        user.setEnabled(true);
        if(user.getEmployee() != null){
            user.getEmployee().getCamping().setEnable(true);
        }
        userRepository.save(user);

        return new MessageDTO(EMessage.USER_SUCESS_ACTIVE);
    }

}
