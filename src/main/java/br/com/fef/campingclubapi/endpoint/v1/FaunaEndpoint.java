package br.com.fef.campingclubapi.endpoint.v1;

import br.com.fef.campingclubapi.endpoint.interfaces.IStandardFilterEndpoint;
import br.com.fef.campingclubapi.service.v1.FaunaService;
import br.com.fef.campingclubapi.dto.FaunaDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static br.com.fef.campingclubapi.util.SwaggerConstants.AUTHORIZATION_HEADER;
import static br.com.fef.campingclubapi.util.SwaggerConstants.VERSION_HEADER;

@RestController
@RequestMapping(headers = "X-API-Version=v1")
public class FaunaEndpoint implements IStandardFilterEndpoint<FaunaDTO> {

    @Autowired
    private FaunaService faunaService;

    @Override
    @GetMapping(path = "/fauna")
    @ApiOperation(value = "Retorna uma lista de faunas")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> list(@RequestParam Optional<Long> id_camping) throws Exception {
        Logger logger = LoggerFactory.getLogger(FaunaEndpoint.class);
        logger.info("List Fauna...");
        return new ResponseEntity<>(faunaService.list(id_camping), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/fauna/{id}")
    @ApiOperation(value = "Retorna uma fauna")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> load(Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(FaunaEndpoint.class);
        logger.info("Load Fauna:" + id);
        return new ResponseEntity<>(faunaService.get(id), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/fauna")
    @ApiOperation(value = "Salva um fauna")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> save(FaunaDTO faunaDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(FaunaEndpoint.class);
        logger.info("Save Fauna...");
        return new ResponseEntity<>(faunaService.save(faunaDTO), HttpStatus.CREATED);
    }

    @Override
    @PutMapping(path = "/fauna/{id}")
    @ApiOperation(value = "Atualiza uma fauna")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> update(Long id, FaunaDTO faunaDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(FaunaEndpoint.class);
        logger.info("Update Fauna:" + id);
        return new ResponseEntity<>(faunaService.update(id, faunaDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/fauna/{id}")
    @ApiOperation(value = "Deleta uma fauna")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> delete(Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(FaunaEndpoint.class);
        logger.info("Delete Fauna:" + id);
        faunaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
