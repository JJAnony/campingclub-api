package br.com.fef.campingclubapi.service.v1;

import br.com.fef.campingclubapi.enums.EMessage;
import br.com.fef.campingclubapi.exception.StandardExeption;
import br.com.fef.campingclubapi.model.Client;
import br.com.fef.campingclubapi.model.PhysicalPerson;
import br.com.fef.campingclubapi.model.User;
import br.com.fef.campingclubapi.repository.UserRepository;
import br.com.fef.campingclubapi.service.interfaces.IStandardMessageService;
import br.com.fef.campingclubapi.dto.ClientDTO;
import br.com.fef.campingclubapi.dto.MessageDTO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IStandardMessageService<ClientDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ClientDTO> list(Pageable pageable) throws Exception {
        List<ClientDTO> clientDTOS = new ArrayList<>();

        Page<User> users = userRepository.findAll(pageable);

        for (User user : users.getContent()) {
            ClientDTO clientDTO = user.toClient();
            clientDTOS.add(clientDTO);
        }

        return clientDTOS;
    }

    @Override
    public ClientDTO get(Long id) throws Exception {

        verifyIfExists(id);

        Optional<User> OpUser = userRepository.findById(id);

        User user = OpUser.get();

        return user.toClient();
    }

    @Override
    public MessageDTO save(ClientDTO clientDTO) throws Exception {

        if (verifyIfExists(clientDTO.getUsername())) {
            throw new StandardExeption(EMessage.USER_ERROR_EXISTS);
        }

        PhysicalPerson person = clientDTO.toPhysicalPerson();

        person.setDate_register(new Date());

        Client client = new Client();

        client.setPerson(person);

        User user = clientDTO.toUser();

        client.setUser(user);

        user.setClient(client);
        user.setPassword(criptyPassword(user.getPassword()));
        user = setCredentials(user);

        userRepository.save(user);

        return new MessageDTO(EMessage.USER_SUCESS_SAVE);
    }

    @Override
    public MessageDTO update(Long id, ClientDTO clientDTO) throws Exception {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Cliente Não Encontrado!"));

        PhysicalPerson person = clientDTO.toPhysicalPerson();
        person.setClient(user.getClient());

        user.getClient().setPerson(person);

        userRepository.save(user);

        return new MessageDTO(EMessage.USER_SUCESS_UPDATE);
    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public void verifyIfExists(Long id) throws Exception {
        if (!userRepository.existsById(id))
            throw new NotFoundException("Usuario do id: ".concat(id.toString()).concat(" Não foi Encontrado!"));
    }

    public boolean verifyIfExists(String username) throws Exception {
        return userRepository.findByUsername(username) != null;
    }

    private String criptyPassword(String pass) {
        return new BCryptPasswordEncoder().encode(pass);
    }

    private User setCredentials(User user) {
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(false);
        return user;
    }

}
