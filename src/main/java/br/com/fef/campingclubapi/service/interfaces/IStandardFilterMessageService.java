package br.com.fef.campingclubapi.service.interfaces;

import br.com.fef.campingclubapi.dto.MessageDTO;

import java.util.List;
import java.util.Optional;

public interface IStandardFilterMessageService<T> {

    List<T> list(Optional<Long> id) throws Exception;

    T get(Long id) throws Exception;

    MessageDTO save(T t) throws Exception;

    MessageDTO update(Long id, T t) throws Exception;

    void delete(Long id) throws Exception;

    void verifyIfExists(Long id) throws Exception;
}
