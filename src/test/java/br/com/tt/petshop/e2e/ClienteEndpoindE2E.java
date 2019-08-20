package br.com.tt.petshop.e2e;

import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteEndpoindE2E {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ClienteService clienteService;

    @Test
  //  @Sql("classpath:e2e/insere-fulano.sql")
    public void obtemClienteFulanoComSucesso() throws Exception {
        Cliente clienteSalvo = clienteService.adicionar(new Cliente(1L, "Carlos Sanin", "111.222.333-36"));
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/"+ clienteSalvo.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(CoreMatchers.equalTo(clienteSalvo.getId().intValue())));

    }

}
