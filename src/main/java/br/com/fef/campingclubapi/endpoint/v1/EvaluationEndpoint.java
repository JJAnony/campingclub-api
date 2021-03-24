package br.com.fef.campingclubapi.endpoint.v1;

import br.com.fef.campingclubapi.endpoint.interfaces.IStandardEndpoint;
import br.com.fef.campingclubapi.service.v1.EvaluationService;
import br.com.fef.campingclubapi.dto.EvaluationDTO;
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
public class EvaluationEndpoint implements IStandardEndpoint<EvaluationDTO> {

    @Autowired
    private EvaluationService evaluationService;

    @Override
    @GetMapping(path = "/evaluetion")
    @ApiOperation(value = "Retorna uma lista de avaliações")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> list(Pageable pageable) throws Exception {
        Logger logger = LoggerFactory.getLogger(EvaluationEndpoint.class);
        logger.info("List Evaluation...");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> load(Long id) throws Exception {
        return null;
    }

    @GetMapping(path = "/evaluation/{id_user}/{id_camping}")
    @ApiOperation(value = "Retorna uma avaliacao")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> load(@PathVariable("id_user") final Long id_user, @PathVariable("id_camping") final Long id_camping) throws Exception {
        Logger logger = LoggerFactory.getLogger(EvaluationEndpoint.class);
        logger.info("Load Evaluation:" + id_user + " and " + id_camping);
        return new ResponseEntity<>(evaluationService.get(id_user, id_camping), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/evaluation")
    @ApiOperation(value = "Salva uma avaliacao")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> save(@RequestBody EvaluationDTO evaluationDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(EvaluationEndpoint.class);
        logger.info("Save Evaluation...");
        return new ResponseEntity<>(evaluationService.save(evaluationDTO), HttpStatus.CREATED);
    }

    @Override
    @PutMapping(path = "/evaluetion/{id}")
    @ApiOperation(value = "Atualiza uma avaliacao")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> update(@PathVariable("id") final Long id, @RequestBody EvaluationDTO evaluationDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(EvaluationEndpoint.class);
        logger.info("Update Evaluation:" + id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/evaluetion/{id}")
    @ApiOperation(value = "Deleta uma avaliacao")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(EvaluationEndpoint.class);
        logger.info("Delete Evaluation:" + id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
