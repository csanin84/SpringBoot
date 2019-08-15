package br.com.tt.petshop.client;

public interface CreditoApiClient {
    CreditoDto verificarSituacao(String cpf);
}
