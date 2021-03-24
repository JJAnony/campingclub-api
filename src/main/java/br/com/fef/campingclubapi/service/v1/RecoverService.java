package br.com.fef.campingclubapi.service.v1;

import br.com.fef.campingclubapi.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecoverService {

    @Autowired
    private UserRepository usuarioRepository;


    public void verifyIfExists(Long id) throws NotFoundException {
        if (!usuarioRepository.existsById(id))
            throw new NotFoundException("Usuario do id: " + id + " foi não Encontrado!");
    }


}
