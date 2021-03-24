package br.com.fef.campingclubapi.exception;

import br.com.fef.campingclubapi.dto.MessageDTO;
import br.com.fef.campingclubapi.util.IconConstants;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestControllerAdvice
public class ControllerExeptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Tratamento de Erros Variados
     *
     * @param ex
     * @param body
     * @param headers
     * @param status
     * @param request
     * @return ResponseEntity
     */
    @Override
    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class, ClassNotFoundException.class})
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> messages = new ArrayList<>();

        if (ex instanceof MethodArgumentNotValidException) {
            List<ObjectError> errors = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
            for (ObjectError error : errors) {
                messages.add(error.getDefaultMessage());
            }
        } else {
            messages.add(ex.getMessage());
        }

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setStatusCode(status.value());
        messageDTO.setIcon(IconConstants.ERROR);
        messageDTO.setMessageTitle(status.getReasonPhrase());
        messageDTO.setMessages(messages);
        return new ResponseEntity<>(messageDTO, headers, status);
    }

    /**
     * Tratamento de Erros do Sistema
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({StandardExeption.class})
    public final ResponseEntity<Object> handleStandardException(final StandardExeption ex, final WebRequest request) {
        return new ResponseEntity<>(ex.toDTO(), ex.getStatus());
    }

    /**
     * Tratamento de Erros de Constraint Violada
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     *
     * Tratamento de Erros de Integridade do Banco
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     *
     * Tratamento de Erros de Sql
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({SQLException.class})
    public ResponseEntity<Object> handleBadRequest(final SQLException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     *
     * Tratamento de Erros Request Mal enviado
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     *
     * Tratamento de Erros Argumento Invalido
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     *
     * Tratamento de Erros Etidade não Instanciada ou não Suportada no banco de dados
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /**
     *
     * Tratamento de Erros de Conexão com o banco
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class})
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    /**
     *
     * Tratamento de Erros Exeções Internas
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
