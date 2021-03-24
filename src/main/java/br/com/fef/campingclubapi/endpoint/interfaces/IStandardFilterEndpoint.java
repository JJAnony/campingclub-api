package br.com.fef.campingclubapi.endpoint.interfaces;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IStandardFilterEndpoint<T> {

    ResponseEntity<?> list(Optional<Long> id) throws Exception;

    ResponseEntity<?> load(Long id) throws Exception;

    ResponseEntity<?> save(T t) throws Exception;

    ResponseEntity<?> update(Long id, T t) throws Exception;

    ResponseEntity<?> delete(Long id) throws Exception;

}
