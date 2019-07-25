package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class ClienteRepository {
    private static Long contadorCliente = 3L;

    List<Cliente> clientes = new ArrayList(
            Arrays.asList(Cliente.newIdClienteNomeCpfInadimplente(1L,"carlos","654.564.153.-15", false),
            Cliente.newIdClienteNomeCpfInadimplente(2L,"raul","452.445.454.-45", false ),
            Cliente.newIdClienteNomeCpfInadimplente(3L,"mario","546.778.567-57",true)));




    public void save(Cliente cliente){
        contadorCliente++;
        cliente.setId(contadorCliente);
        clientes.add(cliente);

    }

    public List<Cliente> findAll() {
        return clientes;
    }

    public Cliente findId(Long clientId) {
        Cliente cliente = findAll()
                .stream()
                .filter(c -> c.getId() ==  clientId)
                .collect(Collectors.toList()).get(0);
        if(!Objects.isNull(cliente))
            return cliente;
        else
            return null;
    }


    public void delete(Cliente cliente) {
        clientes.remove(cliente);
    }
}
