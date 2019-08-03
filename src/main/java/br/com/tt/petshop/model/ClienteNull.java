package br.com.tt.petshop.model;

import br.com.tt.petshop.model.vo.Cpf;

public class ClienteNull extends Cliente{

    public ClienteNull() {
        super.setId(-1L);
        super.setNome("");
        super.setCpf(new Cpf());
        super.setInadimplente(false);
    }
}
