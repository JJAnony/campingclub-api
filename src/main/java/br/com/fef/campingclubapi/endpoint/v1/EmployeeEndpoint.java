package br.com.fef.campingclubapi.endpoint.v1;

import br.com.fef.campingclubapi.endpoint.interfaces.IStandardEndpoint;
import br.com.fef.campingclubapi.service.v1.EmployeeService;
import br.com.fef.campingclubapi.dto.EmployeeDTO;
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
public class EmployeeEndpoint implements IStandardEndpoint<EmployeeDTO> {

    @Autowired
    private EmployeeService employeeService;

    @Override
    @GetMapping(value = "/funcionario")
    @ApiOperation(value = "Retorna uma lista de Funcionarios")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> list(Pageable pageable) throws Exception {
        Logger logger = LoggerFactory.getLogger(EmployeeEndpoint.class);
        logger.info("List Employee...");
        return new ResponseEntity<>(employeeService.list(pageable), HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "funcionario/{id}")
    @ApiOperation(value = "Retorna um Funcionario")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> load(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(EmployeeEndpoint.class);
        logger.info("Load Employee:" + id);
        return new ResponseEntity<>(employeeService.get(id), HttpStatus.OK);
    }

    @Override
    @PostMapping(value = "/funcionario")
    @ApiOperation(value = "Salva um funcionario")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> save(@RequestBody EmployeeDTO employeeDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(EmployeeEndpoint.class);
        logger.info("Save Employee...");
        return new ResponseEntity<>(employeeService.save(employeeDTO), HttpStatus.CREATED);
    }

    @Override
    @PutMapping(value = "/funcionario")
    @ApiOperation(value = "Atualiza um funcionario")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> update(@PathVariable("id") final Long id, @RequestBody EmployeeDTO employeeDTO) throws Exception {
        Logger logger = LoggerFactory.getLogger(EmployeeEndpoint.class);
        logger.info("Update Employee:" + id);
        return new ResponseEntity<>(employeeService.update(id, employeeDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "/funcionario/{id}")
    @ApiOperation(value = "Deleta um funcionario")
    @ApiImplicitParams({
            @ApiImplicitParam(name = AUTHORIZATION_HEADER, value = "Bearer token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = VERSION_HEADER, value = "Version", required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) throws Exception {
        Logger logger = LoggerFactory.getLogger(EmployeeEndpoint.class);
        logger.info("Delete Employee:" + id);
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
