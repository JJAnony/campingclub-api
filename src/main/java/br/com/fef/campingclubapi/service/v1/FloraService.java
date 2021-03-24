package br.com.fef.campingclubapi.service.v1;

import br.com.fef.campingclubapi.model.Camping;
import br.com.fef.campingclubapi.model.Flora;
import br.com.fef.campingclubapi.model.Picture;
import br.com.fef.campingclubapi.model.Plant;
import br.com.fef.campingclubapi.repository.CampingRepository;
import br.com.fef.campingclubapi.repository.FloraRepository;
import br.com.fef.campingclubapi.service.interfaces.IStandardFilterMessageService;
import br.com.fef.campingclubapi.dto.FloraDTO;
import br.com.fef.campingclubapi.dto.MessageDTO;
import br.com.fef.campingclubapi.dto.PlantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FloraService implements IStandardFilterMessageService<FloraDTO> {

    @Autowired
    private CampingRepository campingRepository;

    @Autowired
    private FloraRepository floraRepository;

    @Override
    public List<FloraDTO> list(Optional<Long> id_camping) throws Exception {

        Iterable<Flora> floras;

        if (id_camping.isPresent()) {
            floras = floraRepository.findFlorasByCamping(id_camping.get());
        } else {
            floras = floraRepository.findAll();
        }

        List<FloraDTO> floraDTOS = new ArrayList<>();
        for (Flora flora : floras) {
            List<PlantDTO> plantDTOS = new ArrayList<>();
            for (Plant plant : flora.getPlants()) {
                plantDTOS.add(plant.toPlant());
            }
            FloraDTO floraDTO = flora.toFlora();
            floraDTO.setPlants(plantDTOS);
            floraDTOS.add(floraDTO);
        }

        return floraDTOS;
    }

    @Override
    public FloraDTO get(Long id) throws Exception {
        return null;
    }

    @Override
    public MessageDTO save(FloraDTO floraDTO) throws Exception {
        Flora flora = floraDTO.toFlora();

        Camping camping = campingRepository.findById(floraDTO.getId_camping())
                .orElseThrow(() -> new NullPointerException("Camping Não Encontrado!"));

        flora.setCamping(camping);

        List<Plant> plants = new ArrayList<>();
        for (PlantDTO plantDTO : floraDTO.getPlants()) {
            Plant plant = plantDTO.toPlant();

            List<Picture> pictures = new ArrayList<>();

            pictures.add(plantDTO.toPicture());
            plant.setPictures(pictures);
            plant.setFlora(flora);

            plants.add(plant);
        }

        flora.setPlants(plants);

        floraRepository.save(flora);

        return new MessageDTO();
    }

    @Override
    public MessageDTO update(Long id, FloraDTO floraDTO) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public void verifyIfExists(Long id) throws Exception {

    }
}
