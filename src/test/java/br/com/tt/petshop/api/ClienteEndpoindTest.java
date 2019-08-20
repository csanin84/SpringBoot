package br.com.tt.petshop.api;

import br.com.tt.petshop.config.ModelMapperConfig;
import br.com.tt.petshop.dto.ClienteDto;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.hibernate.validator.internal.metadata.aggregated.rule.OverridingMethodMustNotAlterParameterConstraints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ClienteEndpoind.class)
@Import(ModelMapperConfig.class)

public class ClienteEndpoindTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;


    @Test
    public void deveriaRetornarClientesComSucesso() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string("[]"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deveriaRetornarLisComClienteFulano() throws Exception {
        Mockito.when(clienteService.listar())
                .thenReturn(Arrays.asList(new Cliente(77L, "Fulano Silva", "000-111-222-47")));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/clientes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value((CoreMatchers.equalTo(77))))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deveriaCrearClienteComSucesso() throws Exception {
        Cliente clienteSalvar = new Cliente(33L, "Ciclano Silva", "111-222-333-25");

        Mockito.when(
                clienteService.adicionar(clienteSalvar))
                .thenReturn(clienteSalvar);

        ClienteDto clienteDto = new ClienteDto(33L, "Ciclano Silva", "111-222-333-25");

        byte[] objectToJSon = new ObjectMapper()
                .writeValueAsBytes(clienteDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/clientes")
                .content(objectToJSon)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void deveriaRetornarClientePorId() throws Exception {
        Mockito.when(
                clienteService.findId(33L))
                .thenReturn(Optional.of(new Cliente(33L, "Ciclano Silva", "111.222.333-25")));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/clientes/33"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value((CoreMatchers.equalTo(33))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value((CoreMatchers.equalTo("Ciclano Silva"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value((CoreMatchers.equalTo("111.222.333-25"))))
                .andDo(MockMvcResultHandlers.print());

    }


}