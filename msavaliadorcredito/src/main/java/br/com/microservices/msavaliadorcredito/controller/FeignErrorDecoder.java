package br.com.microservices.msavaliadorcredito.controller;

import br.com.microservices.msavaliadorcredito.exceptions.ResourceNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    /**
     * O método decode é chamado pelo Feign sempre que uma resposta com status
     * de erro é recebida.
     * @param methodKey A assinatura do método do client que foi chamado.
     * @param response A resposta HTTP completa recebida do serviço de destino.
     * @return Uma Exception que será lançada no código cliente.
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        // Verificamos o status da resposta
        if (response.status() == 404) {
            System.err.println("Feign recebeu um 404. Chave do método: " + methodKey);
            // Retornamos nossa exceção de negócio personalizada
            return new ResourceNotFoundException("Recurso não encontrado na chamada para: " + methodKey);
        }

        // Para qualquer outro erro (500, 401, etc.), usamos o decodificador padrão do Feign.
        // Isso é importante para não perdermos o comportamento padrão para outros tipos de erro.
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
