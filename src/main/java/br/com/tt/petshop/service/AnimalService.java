package br.com.tt.petshop.service;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.exeption.AnimalExeption;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final ClienteService clienteService;

    public AnimalService(AnimalRepository animalRepository, ClienteService clienteService) {
        this.animalRepository = animalRepository;
        this.clienteService = clienteService;
    }

    public List<Animal> listar(Long clientId){
        return animalRepository.listar(clientId);
    }

    public void adicionar(Animal animal){
        if(Objects.isNull(animal.getNome())|| Objects.isNull(animal.getClientId()))
            throw new AnimalExeption("Animal sem nome ou sem dono ");
        validarDataMajorHoje(animal.getDataNascimento());
        validarNomeAnimalTresLetras(animal.getNome());
        clienteService.validarClienteInadimplente(animal.getClientId());
        animalRepository.save(animal);
    }

    public List<String> listarEspecies(){

        List<String> listaEspecies = new ArrayList<>();
        for (EspecieEnum especieEnum : EspecieEnum.values()) {
            listaEspecies.add(especieEnum.name());
        }
        return listaEspecies;
    }

    public void remover(Long id){
        //TODO alterar no JPA
        Animal animal = new Animal();
        animal.setId(id);

        animalRepository.delete(animal);
    }

    public boolean validarDataMajorHoje(LocalDate data){
        if(Objects.isNull(data))
            throw new AnimalExeption("Data Invalida");
        if(data.isAfter(LocalDate.now()))
            throw new AnimalExeption("A data debe ser major menor do que hoje");
        return true;
    }

    public boolean validarNomeAnimalTresLetras(String nome){
        String[] partesNome = nome.split(" ");
        for(String parte: partesNome){
            if(parte.length()<3) throw new AnimalExeption("O nome debe ter ao menos 3 caracteres");
        }
        return true;
    }
}
