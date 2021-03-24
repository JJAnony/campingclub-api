package br.com.fef.campingclubapi.endpoint.v1;

import br.com.fef.campingclubapi.endpoint.interfaces.IStandardEndpoint;
import br.com.fef.campingclubapi.service.v1.CampingService;
import br.com.fef.campingclubapi.dto.CampingDTO;
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
public class CampingEndpoint implements IStandardEndpoint<CampingDTO> {

    @Autowired
    private CampingService campingService;

    @Override
    @GetMapping(path = "/camping")
    @ApiOperation(value = "Retorna uma lista de campings")
    @ApiImplicitParams({
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> list(Pageable pageable) throws Exception {
        Logger logger = LoggerFactory.getLogger(CampingEndpoint.class);
        logger.info("List Campings...");
        return new ResponseEntity<>(campingService.list(pageable), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/camping/{id}")
    @ApiOperation(value = "Retorna um camping")
    @ApiImplicitParams({
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> load(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(CampingEndpoint.class);
        logger.info("Load Camping:" + id);
        return new ResponseEntity<>(campingService.get(id), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/camping")
    @ApiOperation(value = "Salva um camping")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> save(@RequestBody CampingDTO campingDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(CampingEndpoint.class);
        logger.info("Save Camping...");
        return new ResponseEntity<>(campingService.save(campingDTO), HttpStatus.CREATED);
    }


    @Override
    @PutMapping(path = "/camping/{id}")
    @ApiOperation(value = "Atualiza um camping")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> update(@PathVariable("id") final Long id, @RequestBody CampingDTO campingDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(CampingEndpoint.class);
        logger.info("Load Camping:" + id);
        return new ResponseEntity<>(campingService.update(id, campingDTO), HttpStatus.OK);
    }


    @Override
    @DeleteMapping(path = "/camping/{id}")
    @ApiOperation(value = "Deleta um camping")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(CampingEndpoint.class);
        logger.info("Delete Camping:" + id);
        campingService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/camping/{id}/files")
    @ApiOperation(value = "Atualiza um camping")
    @ApiImplicitParams({
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> loadFiles(@PathVariable("id") Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(CampingEndpoint.class);
        logger.info("List Camping Files:" + id);
        return null;
    }

}
