package br.com.fef.campingclubapi.service.v1;

import br.com.fef.campingclubapi.repository.FavoriteRepository;
import br.com.fef.campingclubapi.service.interfaces.IStandardService;
import br.com.fef.campingclubapi.dto.FavoriteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService implements IStandardService<FavoriteDTO> {

    @Autowired
    private FavoriteRepository favoritoRepository;

    @Override
    public List<FavoriteDTO> list(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public FavoriteDTO get(Long id) throws Exception {
        return null;
    }

    @Override
    public FavoriteDTO save(FavoriteDTO favoriteDTO) throws Exception {
        return null;
    }

    @Override
    public FavoriteDTO update(Long id, FavoriteDTO favoriteDTO) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public void verifyIfExists(Long id) throws Exception {

    }
}
