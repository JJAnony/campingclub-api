package br.com.fef.campingclubapi.endpoint.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IStandardEndpoint<T> {

    ResponseEntity<?> list(Pageable pageable) throws Exception;

    ResponseEntity<?> load(Long id) throws Exception;

    ResponseEntity<?> save(T t) throws Exception;

    ResponseEntity<?> update(Long id, T t) throws Exception;

    ResponseEntity<?> delete(Long id) throws Exception;

}
