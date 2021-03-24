package br.com.fef.campingclubapi.service.v1;

import br.com.fef.campingclubapi.enums.EMessage;
import br.com.fef.campingclubapi.enums.EUserMail;
import br.com.fef.campingclubapi.exception.StandardExeption;
import br.com.fef.campingclubapi.model.*;
import br.com.fef.campingclubapi.repository.CampingRepository;
import br.com.fef.campingclubapi.repository.UserRepository;
import br.com.fef.campingclubapi.service.interfaces.IStandardMessageService;
import br.com.fef.campingclubapi.dto.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssociatedService implements IStandardMessageService<AssociatedDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CampingRepository campingRepository;

    @Autowired
    private MailService mailService;

    @Override
    public List<AssociatedDTO> list(Pageable pageable) throws Exception {
        List<AssociatedDTO> associatedDTOS = new ArrayList<>();
        Page<User> users = userRepository.findAllByEmployeeIsNotNullAndEmployeeOffice("office", pageable);

        for (User user : users.getContent()) {
            if (user.getEmployee().getOffice() == "root") {
                AssociatedDTO associatedDTO = user.toAssociated();
                associatedDTOS.add(associatedDTO);
            }
        }

        return associatedDTOS;
    }

    @Override
    public AssociatedDTO get(Long id) throws Exception {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Associado Não Encontrado!"));

        AssociatedDTO associatedDTO = user.toAssociated();

        List<FaunaDTO> faunaDTOS = new ArrayList<>();
        for (Fauna fauna : user.getEmployee().getCamping().getFaunas()) {
            List<AnimalDTO> animalDTOS = new ArrayList<>();
            for (Animal animal : fauna.getAnimals()) {
                animalDTOS.add(animal.toAnimal());
            }
            FaunaDTO faunaDTO = fauna.toFauna();
            faunaDTO.setAnimals(animalDTOS);
            faunaDTOS.add(faunaDTO);
        }
        associatedDTO.getCamping().setFaunas(faunaDTOS);

        List<FloraDTO> floraDTOS = new ArrayList<>();
        for (Flora flora : user.getEmployee().getCamping().getFloras()) {
            List<PlantDTO> plantDTOS = new ArrayList<>();
            for (Plant plant : flora.getPlants()) {
                plantDTOS.add(plant.toPlant());
            }
            FloraDTO floraDTO = flora.toFlora();
            floraDTO.setPlants(plantDTOS);
            floraDTOS.add(floraDTO);
        }
        associatedDTO.getCamping().setFloras(floraDTOS);

        List<PictureDTO> pictureDTOS = new ArrayList<>();
        for (Picture picture : user.getEmployee().getCamping().getPictures()) {
            pictureDTOS.add(picture.toPicture());
        }
        associatedDTO.getCamping().setPictures(pictureDTOS);


        return associatedDTO;
    }

    @Override
    public MessageDTO save(AssociatedDTO associatedDTO) throws Exception {

        if (verifyIfExists(associatedDTO.getUsername())) {
            throw new StandardExeption(EMessage.USER_ERROR_EXISTS);
        }

        mailService.sendActivationMessage(EUserMail.GMAIL, associatedDTO.getUsername());

        Address address = associatedDTO.getCamping().toAddress();
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);

        Camping camping = associatedDTO.getCamping().toCamping();
        camping.setAddresses(addresses);

        camping.setEnable(false);

        camping = campingRepository.save(camping);

        PhysicalPerson physicalPerson = associatedDTO.toPhysicalPerson();

        Employee employee = new Employee();
        employee.setPerson(physicalPerson);
        employee.setOffice("root");
        employee.setCamping(camping);

        User user = associatedDTO.toUser();

        employee.setUser(user);

        user.setEmployee(employee);

        user = setCredentials(user);

        user.setPassword(criptyPassword(user.getPassword()));

        userRepository.save(user);

        return new MessageDTO(EMessage.USER_SUCESS_SAVE);
    }

    @Override
    public MessageDTO update(Long id, AssociatedDTO associatedDTO) throws Exception {

        verifyIfExists(id);

        Address address = associatedDTO.getCamping().toAddress();
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);

        Camping camping = associatedDTO.getCamping().toCamping();
        camping.setAddresses(addresses);

        List<Picture> pictures = new ArrayList<>();
        for (PictureDTO pictureDTO : associatedDTO.getCamping().getPictures()) {
            pictures.add(pictureDTO.toPicture());
        }

        camping.setPictures(pictures);


        List<Fauna> faunas = new ArrayList<>();
        for (FaunaDTO faunaDTO : associatedDTO.getCamping().getFaunas()) {
            Fauna fauna = faunaDTO.toFauna();
            fauna.setCamping(camping);

            List<Animal> animals = new ArrayList<>();
            for (AnimalDTO animalDTO : faunaDTO.getAnimals()) {
                Animal animal = animalDTO.toAnimal();

                pictures = new ArrayList<>();
                pictures.add(animalDTO.toPicture());
                animal.setPictures(pictures);
                animal.setFauna(fauna);

                animals.add(animal);
            }
            fauna.setAnimals(animals);
            faunas.add(fauna);
        }

        camping.setFaunas(faunas);

        List<Flora> floras = new ArrayList<>();
        for (FloraDTO floraDTO : associatedDTO.getCamping().getFloras()) {
            Flora flora = floraDTO.toFlora();
            flora.setCamping(camping);

            List<Plant> plants = new ArrayList<>();
            for (PlantDTO plantDTO : floraDTO.getPlants()) {
                Plant plant = plantDTO.toPlant();

                pictures = new ArrayList<>();

                pictures.add(plantDTO.toPicture());
                plant.setPictures(pictures);
                plant.setFlora(flora);

                plants.add(plant);
            }

            flora.setPlants(plants);
            floras.add(flora);
        }

        camping.setFloras(floras);

        camping.setEnable(true);

        camping = campingRepository.save(camping);

        PhysicalPerson physicalPerson = associatedDTO.toPhysicalPerson();

        Employee employee = new Employee();
        employee.setPerson(physicalPerson);
        employee.setOffice("root");
        employee.setCamping(camping);

        User user = associatedDTO.toUser();

        employee.setUser(user);

        user.setEmployee(employee);

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

    public boolean verifyIfExists(String username) {
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
