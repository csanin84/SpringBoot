package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /*
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
    */

}
