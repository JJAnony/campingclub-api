package br.com.fef.campingclubapi.service.v1;

import br.com.fef.campingclubapi.enums.EMessage;
import br.com.fef.campingclubapi.model.Animal;
import br.com.fef.campingclubapi.model.Camping;
import br.com.fef.campingclubapi.model.Fauna;
import br.com.fef.campingclubapi.model.Picture;
import br.com.fef.campingclubapi.repository.CampingRepository;
import br.com.fef.campingclubapi.repository.FaunaRepository;
import br.com.fef.campingclubapi.service.interfaces.IStandardFilterMessageService;
import br.com.fef.campingclubapi.dto.AnimalDTO;
import br.com.fef.campingclubapi.dto.FaunaDTO;
import br.com.fef.campingclubapi.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FaunaService implements IStandardFilterMessageService<FaunaDTO> {

    @Autowired
    private CampingRepository campingRepository;

    @Autowired
    private FaunaRepository faunaRepository;

    @Override
    public List<FaunaDTO> list(Optional<Long> id_camping) throws Exception {

        Iterable<Fauna> faunas;

        if (id_camping.isPresent()) {
            faunas = faunaRepository.findFaunasByCamping(id_camping.get());
        } else {
            faunas = faunaRepository.findAll();
        }

        List<FaunaDTO> faunaDTOS = new ArrayList<>();
        for (Fauna fauna : faunas) {
            List<AnimalDTO> animalDTOS = new ArrayList<>();
            for (Animal animal : fauna.getAnimals()) {
                animalDTOS.add(animal.toAnimal());
            }
            FaunaDTO faunaDTO = fauna.toFauna();
            faunaDTO.setAnimals(animalDTOS);
            faunaDTOS.add(faunaDTO);
        }

        return faunaDTOS;
    }

    @Override
    public FaunaDTO get(Long id) throws Exception {
        return null;
    }

    @Override
    public MessageDTO save(FaunaDTO faunaDTO) throws Exception {

        Fauna fauna = faunaDTO.toFauna();

        Camping camping = campingRepository.findById(faunaDTO.getId_camping())
                .orElseThrow(() -> new NullPointerException("Camping Não Encontrado!"));

        fauna.setCamping(camping);

        List<Animal> animals = new ArrayList<>();
        for (AnimalDTO animalDTO : faunaDTO.getAnimals()) {
            Animal animal = animalDTO.toAnimal();

            List<Picture> pictures = new ArrayList<>();
            pictures.add(animalDTO.toPicture());
            animal.setPictures(pictures);
            animal.setFauna(fauna);

            animals.add(animal);
        }
        fauna.setAnimals(animals);

        return new MessageDTO(EMessage.USER_SUCESS_SAVE);
    }

    @Override
    public MessageDTO update(Long id, FaunaDTO faunaDTO) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public void verifyIfExists(Long id) throws Exception {

    }
}
