package br.com.tt.petshop.service;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.exeption.AnimalExeption;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.AnimalRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static br.com.tt.petshop.enums.EspecieEnum.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AnimalServiceTest {

    private AnimalService animalService;
    @Mock
    private AnimalRepository animalRepository;
    @Mock
    private ClienteService clienteService;

    @Before
    public void setUp(){
        animalService = new AnimalService(animalRepository, clienteService);
    }//fin setUp

    @Test
    public void deveriaRetornarListaVacia(){
        List<Animal> animais = animalService.listar(1L);
        assertNotNull("Alista não deberia ser nula",animais);
        assertEquals("A lista deberia ter 0 clientes",0, animais.size());
    }//fin deveriaRetornarListaVacia

    @Test
    public void deveriaRetornarListaComAnimales(){
        List<Animal> animais_1 = new ArrayList<>(Arrays.asList(
                new Animal( "Rex", LocalDate.now(), EspecieEnum.MAMIFERO, Cliente.newClienteById(1l)),
                new Animal( "Nemo", LocalDate.now(), EspecieEnum.PEIXE, Cliente.newClienteById(1l))

        ));

        List<Animal> animais_2 = new ArrayList<>(Arrays.asList(
                new Animal("Fido", LocalDate.now(), EspecieEnum.MAMIFERO, Cliente.newClienteById(2l))
        ));

        // Mockito.when(clienteRepository.findAll()).thenReturn(listaCliente);
        Mockito.when(animalRepository.findByClienteId(1L)).thenReturn(animais_1);
        Mockito.when(animalRepository.findByClienteId(2L)).thenReturn(animais_2);

        animalService.listar(1L);
        assertEquals("A lista debe conter elementos", 2, animais_1.size());
        animalService.listar(2L);
        assertEquals("A lista debe conter elementos", 1, animais_2.size());

    }//fin deveriaRetornarListaComAnimales

    @Test(expected = AnimalExeption.class)
    public void deveriaLancarAnimalExeptionSemNomeSemDono(){
        Animal animal = new Animal(null, LocalDate.now(), EspecieEnum.MAMIFERO,null);
//        doThrow(AnimalExeption.class).when(animalRepository).save(animal);
        animalService.adicionar(animal);
    }// fin deveriaLancarAnimalExeptionSemNomeSemDono

    @Test
    public void deveriaLacarAnimalExptionDataNull(){
        try{
            Animal animal = new Animal("Kaiser",
                    null,
                    EspecieEnum.MAMIFERO,Cliente.newClienteById(1l));

            animalService.adicionar(animal);
            fail("devia lançar exepção");
        }catch (AnimalExeption e){
            assertEquals("exepção não coincide", "Data Invalida", e.getMessage());
        }
    }//fin deveriaLacarAnimalExptionDataMajorHoje

    @Test
    public void deveriaLacarAnimalExptionDataMajorHoje(){
        try{
            Animal animal = new Animal("Kaiser",
                    LocalDate.of(2020, Month.JUNE, 15),
                    EspecieEnum.MAMIFERO,Cliente.newClienteById(1l));

            animalService.adicionar(animal);
            fail("devia lançar exepção");
        }catch (AnimalExeption e){
            assertEquals("exepção não coincide", "A data debe ser major menor do que hoje", e.getMessage());
        }
    }//fin deveriaLacarAnimalExptionDataMajorHoje

    @Test
    public void deveriaLacarAnimalExeptionNomeMaisDeTresLetras(){
        try{
            Animal animal = new Animal("Ka",
                    LocalDate.of(2018, Month.JUNE, 15),
                    EspecieEnum.MAMIFERO,Cliente.newClienteById(1l));

            animalService.adicionar(animal);
            fail("devia lançar exepção");
        }catch (AnimalExeption e){
            assertEquals("exepção não coincide", "O nome debe ter ao menos 3 caracteres", e.getMessage());
        }

    }// fin deveriaLacarAnimalExeptionNomeMaisDeTresLetras

    @Test(expected = AnimalExeption.class)
    public void deviaLacarAnimalExeptionClienteInadimplente(){

        Animal animal = new Animal("Kaiser",
                LocalDate.of(2018, Month.JUNE, 15),
                EspecieEnum.MAMIFERO,Cliente.newClienteById(1l));

        when(clienteService.validarClienteInadimplente(1L)).thenThrow(AnimalExeption.class);
        animalService.adicionar(animal);
    }// fin deviaLacarAnimalExeptionClienteInadimplente

    @Test
    public void deveriaAdicionarAnimal(){
        Animal animal =  new Animal("Rex", LocalDate.now(), EspecieEnum.MAMIFERO, Cliente.newClienteById(1l));
        animalService.adicionar(animal);
        verify(animalRepository).save(animal);
        verifyNoMoreInteractions(animalRepository);
    }

    @Test
    public void deveriaRemoverComSucesso(){
        Animal animal =  new Animal("", null, null, Cliente.newClienteById(1l));
        animal.setId(1L);
        animalService.remover(1L);

        verify(animalRepository).delete(animal);
        verifyNoMoreInteractions(animalRepository);

    }// fin deveriaRemoverComSucesso

    @Test
    public void deveriaMostrarListaDeEspecies(){
        List<String> especies = animalService.listarEspecies();
        assertNotNull("Objeto Null", especies);
        assertEquals("Tem que ser major do que zero", true, especies.size()>0);
        assertEquals("Listas tem de serem iguais",
                new ArrayList<String>(Arrays.asList(REPTIL.name(),MAMIFERO.name(), PEIXE.name())),
                especies);
    }





}// fin da class AnimalServiceTest
