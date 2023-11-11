package com.algamoney.api.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class AlgaMoneyExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler
    public ResponseEntity<Erro> handleInvalidCredentialsException(
            BadCredentialsException ex) {
        return new ResponseEntity<Erro>(new Erro("Credenciais inválidas", ex.getMessage(), 401 ), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        var messageUser = messageSource.getMessage("recurso.operacao-invalida",
                null, LocaleContextHolder.getLocale());
        var messageDev = ExceptionUtils.getRootCauseMessage(ex);
        Erro erro = new Erro();
        erro.setMsgDev(messageDev);
        erro.setMsgUsuario(messageUser);
        return handleExceptionInternal(ex, erro, new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        var messageUser = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
        var messageDev = ex.toString();
        Erro erro = new Erro();
        erro.setMsgDev(messageDev);
        erro.setMsgUsuario(messageUser);
        return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var messageUser = messageSource
                .getMessage("mensagem.invalida",
                        null, LocaleContextHolder.getLocale());
        List<Erro> erros = criaListaErros(ex.getBindingResult());
        return super
                .handleExceptionInternal(ex,
                        erros,
                        headers,
                        HttpStatus.BAD_REQUEST,
                        request);
    }

    private List<Erro> criaListaErros(BindingResult bindingResult) {
        List<Erro> errors = new ArrayList<>();
        for(FieldError fe : bindingResult.getFieldErrors()) {
            String field = fe.getField();
            Erro erro = new Erro();
            erro.setMsgUsuario(messageSource.getMessage(fe, LocaleContextHolder.getLocale()));
            erro.setMsgDev(fe.toString());
            errors.add(erro);
        }
        return errors;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var messageUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        var messageDev = ex.getCause().toString();
        Erro erro = new Erro();
        erro.setMsgDev(messageDev);
        erro.setMsgUsuario(messageUser);
        return super
                .handleExceptionInternal(ex,
                        List.of(erro),
                        headers,
                        HttpStatus.BAD_REQUEST,
                        request);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Erro {
        private String msgUsuario;
        private String msgDev;
        private int status;
    }
}
