package br.com.tt.petshop.service;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> listar(Long clientId){
        return animalRepository.listar(clientId);
    }

    public void adicionar(Animal animal){
        animalRepository.save(animal);
    }

    public List<String> listarEspecies(){

        List<String> listaEspecies = new ArrayList<>();
        for (EspecieEnum especieEnum : EspecieEnum.values()) {
            listaEspecies.add(especieEnum.name());
        }
        return listaEspecies;
    }
}
