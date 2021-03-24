package br.com.fef.campingclubapi.endpoint.interfaces;

import org.springframework.http.ResponseEntity;

public interface IRecoverEndpoint<T> {

    public ResponseEntity<?> recover();

    public ResponseEntity<?> recover(String hash);


}
