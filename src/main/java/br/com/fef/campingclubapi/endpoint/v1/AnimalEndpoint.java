package br.com.fef.campingclubapi.endpoint.v1;

import br.com.fef.campingclubapi.endpoint.interfaces.IStandardEndpoint;
import br.com.fef.campingclubapi.service.v1.AnimalService;
import br.com.fef.campingclubapi.dto.AnimalDTO;
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
public class AnimalEndpoint implements IStandardEndpoint<AnimalDTO> {

    @Autowired
    private AnimalService animalService;


    @Override
    @GetMapping(path = "/animal")
    @ApiOperation(value = "Retorna uma lista de animais")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> list(Pageable pageable) throws Exception {
        Logger logger = LoggerFactory.getLogger(AnimalEndpoint.class);
        logger.info("List Animails...");
        return new ResponseEntity<>(animalService.list(pageable), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/animal/{id}")
    @ApiOperation(value = "Retorna um animal")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> load(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(AnimalEndpoint.class);
        logger.info("Load Animail:" + id);
        return new ResponseEntity<>(animalService.get(id), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/animal")
    @ApiOperation(value = "Salva um animal")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> save(@RequestBody AnimalDTO animalDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(AnimalEndpoint.class);
        logger.info("Save Animail...");
        return new ResponseEntity<>(animalService.save(animalDTO), HttpStatus.CREATED);
    }

    @Override
    @PutMapping(path = "/animal/{id}")
    @ApiOperation(value = "Atualiza um animal")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> update(@PathVariable("id") final Long id, @RequestBody AnimalDTO animalDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(AnimalEndpoint.class);
        logger.info("Update Animail...");
        return new ResponseEntity<>(animalService.update(id, animalDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/animal/{id}")
    @ApiOperation(value = "Deleta um animal")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(AnimalEndpoint.class);
        logger.info("Delete Animail:" + id);
        animalService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
