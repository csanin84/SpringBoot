package br.com.tt.petshop.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@Component
@FeignClient(url = "https://imersao-credito-api.herokuapp.com/credito/")
public interface CreditoApiFeingClient extends CreditoApiClient {

    @Override
    @GetMapping("/credito/{cpf}")
    CreditoDto verificarSituacao(@PathVariable  String cpf);
}
