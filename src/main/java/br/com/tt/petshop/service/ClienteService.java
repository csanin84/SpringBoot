package br.com.tt.petshop.service;

import br.com.tt.petshop.client.CreditoApiClient;
import br.com.tt.petshop.client.CreditoApiRTClient;
import br.com.tt.petshop.client.CreditoDto;
import br.com.tt.petshop.exeption.AnimalExeption;
import br.com.tt.petshop.exeption.ClienteExeption;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.ClienteRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final CreditoApiClient creditoApiClient;

    public ClienteService(ClienteRepository clienteRepository, CreditoApiClient creditoApiClient) {
        this.clienteRepository = clienteRepository;
        this.creditoApiClient = creditoApiClient;
    }

    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }


    public Cliente adicionar(Cliente novoCliente)  throws ClienteExeption {
        if (Objects.isNull(novoCliente))
            throw new ClienteExeption("cliente nulo");

        validarNome(novoCliente.getNome());
        validarCpf(novoCliente.getCpf().getValor());
        veficarSituacao(novoCliente);

        return clienteRepository.save(novoCliente);
    }

    private void veficarSituacao(Cliente novoCliente) {
        CreditoDto estadoCliente;
        estadoCliente = creditoApiClient.verificarSituacao(novoCliente.getCpf().getValor());

        if (estadoCliente.getSituacao().equals("NEGATIVADO"))
            throw new ClienteExeption("Cliente Negativado! Não pode ser cadastrado");
    }


    public void remover(Long id){
        //TODO alterar no JPA
        Cliente cliente = new Cliente();
        cliente.setId(id);
        clienteRepository.delete(cliente);
    }


    /*-----------------------------------------------------------------------*/
    public Optional<Cliente> findId(Long clientId) {
        return clienteRepository.findById(clientId);
    }

    private boolean validarNome(String nome) throws ClienteExeption{
        if(Objects.isNull(nome))
            throw new ClienteExeption("Nombre nulo");

        String[] partesNome = nome.split(" ");
        if (partesNome.length<2)
            throw new ClienteExeption("O nome debe ter pelo menos 2 partes");

        for(String parte: partesNome){
            if(parte.length()<2) throw new ClienteExeption("O nome debe ter ao menos 2 caracteres");
        }
        return true;
    }

    private boolean validarCpf(String cpf) throws ClienteExeption {
        String sizeCpf = cpf.replaceAll("\\D","");

        if(sizeCpf.length()!=11)
            throw new ClienteExeption("O cpf debe ter 11 caracteres");

        return true;
    }

    public boolean validarClienteInadimplente(Long clientId){
        Cliente cliente = clienteRepository.getOne(clientId);
        if (cliente != null && cliente.getInadimplente())
            throw new AnimalExeption("Cliente inadimplente");
        return false;
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
