package br.com.tt.petshop.repository;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.exeption.AnimalExeption;
import br.com.tt.petshop.model.Animal;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalRepository {
    List<Animal> animais = new ArrayList<>(Arrays.asList(
            new Animal("Rex", LocalDate.now(), EspecieEnum.MAMIFERO, 1l),
            new Animal("Nemo", LocalDate.now(), EspecieEnum.PEIXE, 1L),
            new Animal("Fido", LocalDate.now(), EspecieEnum.MAMIFERO, 2L),
            new Animal("Negra", LocalDate.now(), EspecieEnum.MAMIFERO, 3L)
    ));

    public List<Animal> listar(Long clientId) {
        List<Animal> animaisDoCliente = animais.stream()
                .filter(a -> a.getClientId() ==  clientId)
                .collect(Collectors.toList());
        if (animaisDoCliente.size()==0)
            throw new AnimalExeption("Cliente no tiene Animales");

        return animaisDoCliente;
    }

    public void save(Animal animal){
        animais.add(animal);

    }

    public List<Animal> findAll() {
        return animais;
    }


    public void delete(Animal animal) {
        animais.remove(animal);
    }


}
