package br.com.tt.petshop.repository;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.model.Produto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("classpath:insere_rex.sql")
@ActiveProfiles("test-jpa")
public class AnimalRepositoryIT {
    @Autowired
    private AnimalRepository animalRepository;

    @Test
    public void deveriaRetornarListaVazia() {

        List<Animal> lista = animalRepository.findByClienteId(1L);
        assertEquals("Lista deveria ser lista vazia", 0, lista.size());
    }

    @Test
    public void deveriaRetornarUmAnimal(){
        List<Animal> lista = animalRepository.findByClienteId(133L);
        assertEquals("Deveria retornar um animal",1, lista.size());
        Animal rex = lista.get(0);
        assertEquals("O nome deveria ser Rex", "rex", rex.getNome());
        assertEquals("O cliente deveria ser 133", Long.valueOf(133), rex.getCliente().getId());
        assertEquals("A unidade deberia ser 1", Long.valueOf(1) , rex.getUnidade().getId());
    }

    @Test
    public void deveriaRetornarAnimalPeloNome(){
        List<Animal> lista = animalRepository.findByNome("rex");
        assertEquals("Deveria retornar um animal",1, lista.size());
        Animal rex = lista.get(0);
        assertEquals("O nome deveria ser Rex", "rex", rex.getNome());
        //assertEquals("O cliente deveria ser 133", Long.valueOf(133), rex.getCliente().getId());
       // assertEquals("A unidade deberia ser 1", Long.valueOf(1) , rex.getUnidade().getId());*/
    }

    @Test
    public void deveriaRetornarListaProdutosByIdAnimal(){
        List<Produto> lista = animalRepository.findProdutosByIdAnimal(1L);
        assertEquals("Deveria retornar um animal",2, lista.size());
        assertEquals("Deveria retornar Vacina aftosal","Vacina aftosa", lista.get(0).getDescricao());
    }

    @Test
    public void deveriaRetornarAnimalPorPeriodoEEspecie(){
        List<Animal> lista = animalRepository.findByDataNascimentoDataBetweenAndEspecieIs(LocalDate.parse("2018-01-02"),
                LocalDate.parse("2019-07-02"), EspecieEnum.MAMIFERO);

        assertEquals("Deveria retornar um animal",1, lista.size());
        assertEquals("Deveria retornar rex","rex", lista.get(0).getNome());
        assertEquals("Deveria retornar Mamifero",EspecieEnum.MAMIFERO, lista.get(0).getEspecie());
    }
}