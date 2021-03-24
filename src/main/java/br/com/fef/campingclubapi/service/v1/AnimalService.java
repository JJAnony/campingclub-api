package br.com.fef.campingclubapi.service.v1;

import br.com.fef.campingclubapi.repository.AnimalRepository;
import br.com.fef.campingclubapi.service.interfaces.IStandardService;
import br.com.fef.campingclubapi.dto.AnimalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService implements IStandardService<AnimalDTO> {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public List<AnimalDTO> list(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public AnimalDTO get(Long id) throws Exception {
        return null;
    }

    @Override
    public AnimalDTO save(AnimalDTO animalDTO) throws Exception {
        return null;
    }

    @Override
    public AnimalDTO update(Long id, AnimalDTO animalDTO) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {
        animalRepository.deleteById(id);
    }

    @Override
    public void verifyIfExists(Long id) throws Exception {

    }
}
