package br.com.microservices.msavaliadorcredito.controller;

import br.com.microservices.msavaliadorcredito.exceptions.ErrorDetails;
import br.com.microservices.msavaliadorcredito.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApplicationControllerAdvice {
    /**
     * Este método trata especificamente a RecursoNaoEncontradoException.
     * A anotação @ExceptionHandler indica que este método será chamado quando
     * uma exceção do tipo RecursoNaoEncontradoException for lançada.
     * * @param ex A exceção que foi capturada.
     * @param request O contexto da requisição web atual.
     * @return Um ResponseEntity contendo o corpo do erro (ErroResposta) e o status HTTP apropriado.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleRecursoNaoEncontradoException(ResourceNotFoundException ex, WebRequest request) {
        // Cria o nosso objeto de resposta de erro padronizado.
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(), // Usa a mensagem da sua exceção
                request.getDescription(false).replace("uri=", "")
        );

        // Retorna o ResponseEntity com o objeto de erro e o status HTTP 404.
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Este método trata qualquer outra exceção não capturada anteriormente.
     * É um handler genérico para garantir que nenhuma exceção passe sem tratamento,
     * evitando que detalhes sensíveis do sistema (como stack traces) vazem para o cliente.
     * * @param ex A exceção genérica que foi capturada.
     * @param request O contexto da requisição web atual.
     * @return Um ResponseEntity com uma mensagem de erro genérica e o status HTTP 500.
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Ocorreu um erro inesperado: " + ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
