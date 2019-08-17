package br.com.tt.petshop.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ClienteNotFoundExeption extends ClienteExeption {
    private final Long ClienteId;
    public ClienteNotFoundExeption(Long clienteId) {
        super("Cliente não existe");
        this.ClienteId = clienteId;
    }

    public Long getClienteId() {
        return ClienteId;
    }
}
