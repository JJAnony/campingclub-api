package br.com.fef.campingclubapi.endpoint.v1;

import br.com.fef.campingclubapi.endpoint.interfaces.IStandardEndpoint;
import br.com.fef.campingclubapi.service.v1.CommentService;
import br.com.fef.campingclubapi.dto.CommentDTO;
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
public class CommentEndpoint implements IStandardEndpoint<CommentDTO> {

    @Autowired
    private CommentService commentService;

    @Override
    @GetMapping(path = "/comment")
    @ApiOperation(value = "Retorna uma lista de comentarios")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> list(Pageable pageable) throws Exception {
        Logger logger = LoggerFactory.getLogger(CommentEndpoint.class);
        logger.info("List Comments...");
        return new ResponseEntity<>(commentService.list(pageable), HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/comment/{id}")
    @ApiOperation(value = "Retorna um comentario")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> load(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(CommentEndpoint.class);
        logger.info("Load Comment:" + id);
        return new ResponseEntity<>(commentService.get(id), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/comment")
    @ApiOperation(value = "Salva um comentario")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> save(@RequestBody CommentDTO commentDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(CommentEndpoint.class);
        logger.info("Save Comment...");
        return new ResponseEntity<>(commentService.save(commentDTO), HttpStatus.CREATED);
    }

    @Override
    @PutMapping(path = "/comment/{id}")
    @ApiOperation(value = "Atualiza um comentario")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> update(@PathVariable("id") final Long id, @RequestBody CommentDTO commentDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(CommentEndpoint.class);
        logger.info("Update Comment:" + id);
        return new ResponseEntity<>(commentService.update(id, commentDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/comentario/{id}")
    @ApiOperation(value = "Deleta um camping")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(CommentEndpoint.class);
        logger.info("Delete Comment:" + id);
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
