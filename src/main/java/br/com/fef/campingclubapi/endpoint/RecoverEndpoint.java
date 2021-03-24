package br.com.fef.campingclubapi.endpoint;

import br.com.fef.campingclubapi.endpoint.interfaces.IRecoverEndpoint;
import br.com.fef.campingclubapi.model.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecoverEndpoint implements IRecoverEndpoint<User> {


    @Override
    @PostMapping(path = "/recover")
    @ApiOperation(value = "Gera uma hash e envia por email")
    public ResponseEntity<?> recover() {
        Long id = null;
        return new ResponseEntity<>("Hash Gerada ok", HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/recover/{hash}")
    @ApiOperation(value = "Carrega um usuario a partir de uma hash")
    public ResponseEntity<?> recover(@PathVariable("hash") final String hash) {
        Long id = null;
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}
