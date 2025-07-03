package br.com.microservices.msavaliadorcredito.exceptions;

public class ErroSolicitarCartaoException extends RuntimeException{
    public ErroSolicitarCartaoException(String message) {
        super(message);
    }
}
