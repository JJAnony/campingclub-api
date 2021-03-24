package br.com.fef.campingclubapi.endpoint.v1;

import br.com.fef.campingclubapi.endpoint.interfaces.IStandardEndpoint;
import br.com.fef.campingclubapi.service.v1.ClientService;
import br.com.fef.campingclubapi.dto.ClientDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.fef.campingclubapi.util.SwaggerConstants.AUTHORIZATION_HEADER;
import static br.com.fef.campingclubapi.util.SwaggerConstants.VERSION_HEADER;

@RestController
@RequestMapping(headers = "X-API-Version=v1")
public class ClientEndpoint implements IStandardEndpoint<ClientDTO> {


    @Autowired
    private ClientService clientService;

    @Override
    @GetMapping(path = "/client")
    @ApiOperation(value = "Retorna uma lista de Clientes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> list(Pageable pageable) throws Exception {
        Logger logger = LoggerFactory.getLogger(ClientEndpoint.class);
        logger.info("List Clients...");
        return new ResponseEntity<>(clientService.list(pageable), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/client/{id}")
    @ApiOperation(value = "Retorna um Cliente")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> load(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(ClientEndpoint.class);
        logger.info("Load Client:" + id);
        return new ResponseEntity<>(clientService.get(id), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/client")
    @ApiOperation(value = "Salva um Cliente")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> save(@RequestBody @Valid ClientDTO clientDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(CampingEndpoint.class);
        logger.info("Save Client...");
        return new ResponseEntity<>(clientService.save(clientDTO), HttpStatus.CREATED);
    }

    @Override
    @PostMapping(path = "/client/{id}")
    @ApiOperation(value = "Atualiza um Cliente")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> update(@PathVariable("id") final Long id, @RequestBody ClientDTO clientDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(CampingEndpoint.class);
        logger.info("Update Client:" + id);
        return new ResponseEntity<>(clientService.update(id, clientDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/client/{id}")
    @ApiOperation(value = "Deleta um Cliente")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(CampingEndpoint.class);
        logger.info("Delete Client:" + id);
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
