package br.com.tt.petshop.service;

import br.com.tt.petshop.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ClienteService {
    public List<Cliente> listar(){
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(Cliente.newClienteNomeCpf("carlos","654564153415"));
        clientes.add(Cliente.newClienteNomeCpf("raul","452445454545"));
        clientes.add(Cliente.newClienteNomeCpf("mario","54677856757"));
       return clientes;
    }
}
