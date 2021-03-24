package br.com.fef.campingclubapi.service.interfaces;

import br.com.fef.campingclubapi.dto.MessageDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStandardMessageService<T> {

    List<T> list(Pageable pageable) throws Exception;

    T get(Long id) throws Exception;

    MessageDTO save(T t) throws Exception;

    MessageDTO update(Long id, T t) throws Exception;

    void delete(Long id) throws Exception;

    void verifyIfExists(Long id) throws Exception;
}
