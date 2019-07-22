package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class ClienteRepository {

    List<Cliente> clientes = new ArrayList(
            Arrays.asList(Cliente.newIdClienteNomeCpf("carlos","654.564.153.-15"),
            Cliente.newIdClienteNomeCpf("raul","452.445.454.-45" ),
            Cliente.newIdClienteNomeCpf("mario","546.778.567-57")));




    public void save(Cliente cliente){
        clientes.add(cliente);

    }

    public List<Cliente> findAll() {
        return clientes;
    }


    public void delete(Cliente cliente) {
        clientes.remove(cliente);
    }
}
