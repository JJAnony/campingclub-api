package br.com.fef.campingclubapi.endpoint.v1;

import br.com.fef.campingclubapi.endpoint.interfaces.IStandardEndpoint;
import br.com.fef.campingclubapi.service.v1.AssociatedService;
import br.com.fef.campingclubapi.dto.AssociatedDTO;
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
public class AssociatedEndpoint implements IStandardEndpoint<AssociatedDTO> {

    @Autowired
    private AssociatedService associatedService;

    @Override
    @GetMapping(path = "/associated")
    @ApiOperation(value = "List Associateds")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> list(Pageable pageable) throws Exception {
        Logger logger = LoggerFactory.getLogger(AssociatedEndpoint.class);
        logger.info("List Associateds...");
        return new ResponseEntity<>(associatedService.list(pageable), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/associated/{id}")
    @ApiOperation(value = "Load Associated by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> load(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(AssociatedEndpoint.class);
        logger.info("Load Associated:" + id);
        return new ResponseEntity<>(associatedService.get(id), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/associated")
    @ApiOperation(value = "Save Associated")
    @ApiImplicitParams({
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> save(@RequestBody @Valid AssociatedDTO associatedDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(AssociatedEndpoint.class);
        logger.info("Save Associated");
        return new ResponseEntity<>(associatedService.save(associatedDTO), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/associated/{id}")
    @ApiOperation(value = "Update Associated")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> update(@PathVariable("id") final Long id, @RequestBody AssociatedDTO associatedDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(AssociatedEndpoint.class);
        logger.info("Update Associated");
        return new ResponseEntity<>(associatedService.update(id, associatedDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/associated/{id}")
    @ApiOperation(value = "Delete Associated")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(AssociatedEndpoint.class);
        logger.info("Deleted Associated:" + id);
        associatedService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
