package br.com.fef.campingclubapi.endpoint.v1;

import br.com.fef.campingclubapi.endpoint.interfaces.IStandardEndpoint;
import br.com.fef.campingclubapi.service.v1.FavoriteService;
import br.com.fef.campingclubapi.dto.FavoriteDTO;
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
public class FavoriteEndpoint implements IStandardEndpoint<FavoriteDTO> {

    @Autowired
    private FavoriteService favoriteService;

    @Override
    @GetMapping(path = "/favorito")
    @ApiOperation(value = "Retorna uma lista de favoritos")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> list(Pageable pageable) throws Exception {
        Logger logger = LoggerFactory.getLogger(FavoriteEndpoint.class);
        logger.info("List Favorite...");
        return new ResponseEntity<>(favoriteService.list(pageable), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/favorito/{id}")
    @ApiOperation(value = "Retorna um favorito")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> load(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(FavoriteEndpoint.class);
        logger.info("Load Favorite:" + id);
        return new ResponseEntity<>(favoriteService.get(id), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/favorito")
    @ApiOperation(value = "Salva um favorito")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> save(@RequestBody FavoriteDTO favoriteDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(FavoriteEndpoint.class);
        logger.info("Save Favorite...");
        return new ResponseEntity<>(favoriteService.save(favoriteDTO), HttpStatus.CREATED);
    }

    @Override
    @PutMapping(path = "/favorito/{id}")
    @ApiOperation(value = "Atualiza um favorito")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> update(@PathVariable("id") final Long id, @RequestBody FavoriteDTO favoriteDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(FavoriteEndpoint.class);
        logger.info("Update Favorite:" + id);
        return new ResponseEntity<>(favoriteService.update(id, favoriteDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/favorito/{id}")
    @ApiOperation(value = "Deleta um favorito")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(FavoriteEndpoint.class);
        logger.info("Delete Favorite:" + id);
        favoriteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
