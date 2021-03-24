package br.com.fef.campingclubapi.endpoint.v1;

import br.com.fef.campingclubapi.service.v1.EnableService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.fef.campingclubapi.util.SwaggerConstants.VERSION_HEADER;

@RestController
@RequestMapping(path = "/enable", headers = "X-API-Version=v1")
public class EnableEndpoint {

    @Autowired
    private EnableService enableService;

    @PostMapping(path = "/active")
    @ApiOperation(value = "Deleta um usuario")
    @ApiImplicitParams({
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> active(@RequestBody String code) throws Exception {
        Logger logger = LoggerFactory.getLogger(EnableEndpoint.class);
        logger.info("Ativando Usuario pelo Codigo:" + code);
        return new ResponseEntity<>(enableService.active(code), HttpStatus.OK);

    }
}
