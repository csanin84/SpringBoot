package br.com.tt.petshop.model;

public class ClienteNull extends Cliente{

    public ClienteNull() {
        super.setId(-1L);
        super.setNome("");
        super.setCpf("");
        super.setInadimplente(false);
    }
}
