package br.com.fef.campingclubapi.service.v1;

import br.com.fef.campingclubapi.model.*;
import br.com.fef.campingclubapi.repository.CampingRepository;
import br.com.fef.campingclubapi.service.interfaces.IStandardService;
import br.com.fef.campingclubapi.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CampingService implements IStandardService<CampingDTO> {

    @Autowired
    private CampingRepository campingRepository;

    @Override
    public List<CampingDTO> list(Pageable pageable) throws Exception {

        List<CampingDTO> campingDTOS = new ArrayList<>();

        Iterable<Camping> campings = campingRepository.findAll(pageable);

        for (Camping camping : campings) {
            if(camping.isEnable()) {
                CampingDTO campingDTO = camping.toCamping();

                for (Picture picture : camping.getPictures()) {
                    if (picture.getMain()) {
                        PictureDTO pictureDTO = picture.toPicture();
                        campingDTO.getPictures().add(pictureDTO);
                    }
                }

                campingDTOS.add(campingDTO);
            }
        }

        return campingDTOS;
    }

    @Override
    public CampingDTO get(Long id) throws Exception {
        verifyIfExists(id);

        Optional<Camping> OpCamping = campingRepository.findById(id);

        Camping camping = OpCamping.get();

        CampingDTO campingDTO = camping.toCamping();

        List<PictureDTO> pictureDTOS = new ArrayList<>();
        for (Picture picture : camping.getPictures()) {
            pictureDTOS.add(picture.toPicture());
        }

        campingDTO.setPictures(pictureDTOS);

        List<FaunaDTO> faunaDTOS = new ArrayList<>();
        for(Fauna fauna: camping.getFaunas()){
            FaunaDTO faunaDTO = fauna.toFauna();
            List<AnimalDTO> animalDTOS = new ArrayList<>();
            for(Animal animal: fauna.getAnimals()){
                animalDTOS.add(animal.toAnimal());
            }
            faunaDTO.setAnimals(animalDTOS);
            faunaDTOS.add(faunaDTO);
        }
        campingDTO.setFaunas(faunaDTOS);

        List<FloraDTO> floraDTOS = new ArrayList<>();
        for (Flora flora : camping.getFloras()) {
            List<PlantDTO> plantDTOS = new ArrayList<>();
            for(Plant plant: flora.getPlants()){
                plantDTOS.add(plant.toPlant());
            }
            FloraDTO floraDTO = flora.toFlora();
            floraDTO.setPlants(plantDTOS);
            floraDTOS.add(floraDTO);
        }
        campingDTO.setFloras(floraDTOS);

        List<CommentDTO> commentDTOS = new ArrayList<>();
        for(Comment comment: camping.getComments()){
            commentDTOS.add(comment.toComment());
        }

        campingDTO.setComments(commentDTOS);

        return campingDTO;
    }

    @Override
    public CampingDTO save(CampingDTO campingDTO) throws Exception {
        return null;
    }

    @Override
    public CampingDTO update(Long id, CampingDTO campingDTO) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }


    @Override
    public void verifyIfExists(Long id) throws Exception {
        if (!campingRepository.existsById(id)) {
            throw new Exception("Camping Não Encontrado!");
        }
    }
}
