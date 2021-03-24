package br.com.fef.campingclubapi.endpoint.v1;

import br.com.fef.campingclubapi.endpoint.interfaces.IStandardFilterEndpoint;
import br.com.fef.campingclubapi.service.v1.FloraService;
import br.com.fef.campingclubapi.dto.FloraDTO;
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
public class FloraEndpoint implements IStandardFilterEndpoint<FloraDTO> {

    @Autowired
    private FloraService floraService;

    @Override
    @GetMapping(path = "/flora")
    @ApiOperation(value = "Retorna uma lista de floras")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> list(Optional<Long> id_camping) throws Exception {
        Logger logger = LoggerFactory.getLogger(FloraEndpoint.class);
        logger.info("List Flora...");
        return new ResponseEntity<>(floraService.list(id_camping), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/flora/{id}")
    @ApiOperation(value = "Retorna uma flora")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> load(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(FloraEndpoint.class);
        logger.info("Load Flora:" + id);
        return new ResponseEntity<>(floraService.get(id), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/flora")
    @ApiOperation(value = "Salva uma flora")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> save(@RequestBody FloraDTO floraDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(FloraEndpoint.class);
        logger.info("Save Flora...");
        return new ResponseEntity<>(floraService.save(floraDTO), HttpStatus.CREATED);
    }

    @Override
    @PutMapping(path = "/flora/{id}")
    @ApiOperation(value = "Atualiza uma flora")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> update(@PathVariable("id") final Long id, @RequestBody FloraDTO floraDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(FloraEndpoint.class);
        logger.info("Update Flora:" + id);
        return new ResponseEntity<>(floraService.update(id, floraDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/flora/{id}")
    @ApiOperation(value = "Deleta uma flora")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(FavoriteEndpoint.class);
        logger.info("Delete Flora:" + id);
        floraService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
