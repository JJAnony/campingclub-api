package br.com.fef.campingclubapi.service.v1;

import br.com.fef.campingclubapi.repository.PlantaRepository;
import br.com.fef.campingclubapi.service.interfaces.IStandardService;
import br.com.fef.campingclubapi.dto.PlantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService implements IStandardService<PlantDTO> {

    @Autowired
    private PlantaRepository plantaRepository;


    @Override
    public List<PlantDTO> list(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public PlantDTO get(Long id) throws Exception {
        return null;
    }

    @Override
    public PlantDTO save(PlantDTO plantDTO) throws Exception {
        return null;
    }

    @Override
    public PlantDTO update(Long id, PlantDTO plantDTO) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {
        plantaRepository.deleteById(id);
    }

    @Override
    public void verifyIfExists(Long id) throws Exception {

    }
}
