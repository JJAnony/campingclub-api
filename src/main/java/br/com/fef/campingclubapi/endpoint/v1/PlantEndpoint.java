package br.com.fef.campingclubapi.endpoint.v1;

import br.com.fef.campingclubapi.endpoint.interfaces.IStandardEndpoint;
import br.com.fef.campingclubapi.service.v1.PlantService;
import br.com.fef.campingclubapi.dto.PlantDTO;
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

import static br.com.fef.campingclubapi.util.SwaggerConstants.AUTHORIZATION_HEADER;
import static br.com.fef.campingclubapi.util.SwaggerConstants.VERSION_HEADER;

@RestController
@RequestMapping(headers = "X-API-Version=v1")
public class PlantEndpoint implements IStandardEndpoint<PlantDTO> {


    @Autowired
    private PlantService plantService;

    @Override
    @GetMapping(path = "/plant")
    @ApiOperation(value = "Retorna uma lista de plantas")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> list(Pageable pageable) throws Exception {
        Logger logger = LoggerFactory.getLogger(PlantEndpoint.class);
        logger.info("List Plant...");
        return new ResponseEntity<>(plantService.list(pageable), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/plant/{id}")
    @ApiOperation(value = "Retorna uma planta")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> load(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(PlantEndpoint.class);
        logger.info("Load Plant:" + id);
        return new ResponseEntity<>(plantService.get(id), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/plant")
    @ApiOperation(value = "Salva um planta")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> save(@RequestBody PlantDTO plantDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(PlantEndpoint.class);
        logger.info("Save Plant...");
        return new ResponseEntity<>(plantService.save(plantDTO), HttpStatus.CREATED);
    }

    @Override
    @PutMapping(path = "/plant/{id}")
    @ApiOperation(value = "Atualiza uma planta")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> update(@PathVariable("id") final Long id, @RequestBody PlantDTO plantDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(PlantEndpoint.class);
        logger.info("Update Plant:" + id);
        return new ResponseEntity<>(plantService.update(id, plantDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/plant/{id}")
    @ApiOperation(value = "Deleta uma planta")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(PlantEndpoint.class);
        logger.info("Delete Plant:" + id);
        plantService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
