package com.app.sistema_pedidos.infra.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity tratarErroDuplicado(DataIntegrityViolationException ex) {
        String mensagemErro = ex.getRootCause() != null ? ex.getRootCause().getMessage() : "";

        // Verifica se no texto do erro do Postgres menciona CPF
        if (mensagemErro.contains("clientes_cpf_key")) {
            return ResponseEntity.badRequest().body(List.of(
                    new DadosErroValidacao("cpf", "Este CPF já está cadastrado no sistema.")
            ));
        }

        // Verifica também para o e-mail
        if (mensagemErro.contains("clientes_email_key")) {
            return ResponseEntity.badRequest().body(List.of(
                    new DadosErroValidacao("email", "Este e-mail já está cadastrado no sistema.")
            ));
        }

        // Se for outro erro de integridade genérico
        return ResponseEntity.badRequest().body("Erro de integridade de dados no banco de dados.");
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError field) {
            this(field.getField(), field.getDefaultMessage());
        }
    }
}
