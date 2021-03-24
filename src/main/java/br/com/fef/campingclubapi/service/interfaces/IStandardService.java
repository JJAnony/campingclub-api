package br.com.fef.campingclubapi.service.interfaces;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStandardService<T> {

    List<T> list(Pageable pageable) throws Exception;

    T get(Long id) throws Exception;

    T save(T t) throws Exception;

    T update(Long id, T t) throws Exception;

    void delete(Long id) throws Exception;

    void verifyIfExists(Long id) throws Exception;
}
