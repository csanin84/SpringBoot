package br.com.tt.petshop.service;

import br.com.tt.petshop.exeption.AnimalExeption;
import br.com.tt.petshop.exeption.ClienteExeption;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.ClienteRepository;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

    private ClienteService clienteService;
    @Mock
    private ClienteRepository clienteRepository;

    @Before
    public void setUp(){
        clienteService = new ClienteService(clienteRepository);
    }

    @Test
    public void deveriaRetornarListaVacia(){

       // clienteRepository = new ClienteRepository();
        //clienteService = new ClienteService(clienteRepository);

        List<Cliente> clientes = clienteService.listar();

        assertNotNull("Alista não deberia ser nula",clientes);
        assertEquals("A lista deberia ter 0 clientes",0, clientes.size());
    }

    @Test
    public void deveriaRetornarListaComClientes(){
        // Arange - Setup
        List<Cliente> listaCliente = new ArrayList<>( Arrays.asList(
                Cliente.newIdClienteNomeCpfInadimplente(1L,"carlos","654.564.153.-15", false),
                Cliente.newIdClienteNomeCpfInadimplente(2L,"raul","452.445.454.-45", false ),
                Cliente.newIdClienteNomeCpfInadimplente(3L,"mario","546.778.567-57",true)));

        Mockito.when(clienteRepository.findAll()).thenReturn(listaCliente);

        // Act - Execução
        List<Cliente> clientes = clienteService.listar();

        // Assert - Verificação
        assertTrue("A lista debe conter elementos",clientes.size()>0);
    }


    @Test
    public void deveriaRemoverComSucesso(){
        // Arange - Setup
        clienteService.remover(12L);

        // Assert
        Cliente clienteDeletado = Cliente.newIdClienteNomeCpfInadimplente(12L,"","",false);
        verify(clienteRepository).delete(clienteDeletado);
        verifyNoMoreInteractions(clienteRepository);

    }

    /*-------------------------------------Cenario Adicionar Cliente---------------------------------------*/
    @Test
    public void deveriaLacarClienteExeptionNull(){
        try{
            clienteService.adicionar(null);
            fail("deveria ter lançado exceção");
        }catch (ClienteExeption e){
            assertEquals("Expção não coincide","cliente nulo", e.getMessage());
        }
    }

    @Test
    public void deveriaLacarClienteExeptionNullNomeNull(){
        try{
            Cliente cliente = Cliente.newIdClienteNomeCpfInadimplente(1L,null,
                    "000.111.222-38", false);
            clienteService.adicionar(cliente);
            fail("deveria ter lançado exceção");
        }catch (ClienteExeption e){
            assertEquals("Expção não coincide","Nombre nulo", e.getMessage());
        }
    }

    @Test
    public void deveriaLacarClienteExeptionNullNomeDuasPartes(){
        try{
            Cliente cliente = Cliente.newIdClienteNomeCpfInadimplente(1L,"Carlos",
                    "000.111.222-38", false);
            clienteService.adicionar(cliente);
            fail("deveria ter lançado exceção");
        }catch (ClienteExeption e){
            assertEquals("Expção não coincide","O nome debe ter pelo menos 2 partes", e.getMessage());
        }
    }

    @Test
    public void deveriaLacarClienteExeptionNullNomeDoisCaracteres(){
        try{
            Cliente cliente = Cliente.newIdClienteNomeCpfInadimplente(1L,"C Sanin",
                    "000.111.222-38", false);
            clienteService.adicionar(cliente);
            fail("deveria ter lançado exceção");
        }catch (ClienteExeption e){
            assertEquals("Expção não coincide","O nome debe ter ao menos 2 caracteres", e.getMessage());
        }
    }

    @Test
    public void deveriaLacarClienteExeptionNullCpfOnceNumeros(){
        try{
            Cliente cliente = Cliente.newIdClienteNomeCpfInadimplente(1L,"Carlos Sanin",
                    "00011122238789", false);
            clienteService.adicionar(cliente);
            fail("deveria ter lançado exceção");
        }catch (ClienteExeption e){
            assertEquals("Expção não coincide","O cpf debe ter 11 caracteres", e.getMessage());
        }
    }

    @Test
    public void deveriaLacarClienteExeptionIsInadimplente(){
        try{
            Long clienteId = 1L;
            Cliente cliente = Cliente.newIdClienteNomeCpfInadimplente(1L,"Carlos Sanin",
                    "000.111.222-38", true);

            Mockito.when(clienteRepository.findId(clienteId)).thenReturn(cliente);

            clienteService.validarClienteInadimplente(clienteId);

            fail("deveria ter lançado exceção");
        }catch (AnimalExeption e){
            assertEquals("Expção não coincide","Cliente inadimplente", e.getMessage());
        }
    }

    @Test
    public void deveriaLacarClienteExeptionIsAdimplente(){
            Long clienteId = 1L;
            Cliente cliente = Cliente.newIdClienteNomeCpfInadimplente(1L,"Carlos Sanin",
                    "000.111.222-38", false);

            Mockito.when(clienteRepository.findId(clienteId)).thenReturn(cliente);

            clienteService.validarClienteInadimplente(clienteId);

           assertTrue(clienteService.validarClienteInadimplente(clienteId));
    }

    @Test
    public void deveriaAdicionarCliente(){
            Cliente novoCliente = Cliente.newIdClienteNomeCpfInadimplente(1L,"Carlos Sanin",
                    "000.111.222-38", false);
            clienteService.adicionar(novoCliente);
            verify(clienteRepository).save(novoCliente);
            verifyNoMoreInteractions(clienteRepository);

    }
    /*-------------------------------------fin cenario adicionar-----------------------------------*/

    @Test
    public void deveriaEncontrarClientePeloId(){
        Long clienteId = 1L;
        Cliente cliente = Cliente.newIdClienteNomeCpfInadimplente(clienteId,"Carlos Sanin",
                "000.111.222-38", false);

        Mockito.when(clienteRepository.findId(clienteId)).thenReturn(cliente);

        assertEquals("Cliente não fue achado", cliente, clienteService.findId(clienteId));
    }

    @Test
    public void deveriaNaoEncontrarClientePeloId(){
        Long clienteId = 1L;

        Mockito.when(clienteRepository.findId(clienteId)).thenReturn(null);

        Cliente clienteAchado = clienteService.findId(clienteId);

        assertEquals("Cliente não fue achado", null, clienteAchado);
    }
}

