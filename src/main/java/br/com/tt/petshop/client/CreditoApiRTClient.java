package br.com.tt.petshop.client;

import br.com.tt.petshop.exeption.ClienteExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@Component
public class CreditoApiRTClient implements CreditoApiClient {
    private final RestTemplate restTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditoApiRTClient.class);

    public CreditoApiRTClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CreditoDto verificarSituacao(String cpf){
        URI url = URI
                .create("https://imersao-credito-api.herokuapp.com/credito/" + cpf);
        try{
            return restTemplate.getForObject(url, CreditoDto.class);
        }catch (HttpClientErrorException e){
            LOGGER.info("Erro ao acessar serviço de credito", e);
            if(e.getStatusCode().is4xxClientError())
                throw new ClienteExeption("Verifique o CPF enviado. Detalles:" + e.getMessage());
            else
                throw  new ClienteExeption("Serviço de consulta ao credito indisponivel");
        }

    }
}
