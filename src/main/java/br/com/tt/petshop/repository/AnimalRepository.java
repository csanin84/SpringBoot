package br.com.tt.petshop.repository;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.exeption.AnimalExeption;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@Service
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByClienteId(Long cliente);

    List<Animal> findByNome(String nome);

    /*@Query("select p from Produto p, Animal a where a.id = :id and p.animal.id = a.id")
    List<Produto> findProdutosByIdAnimal(@Param("id") Long id);*/

    @Query("SELECT p FROM Animal a  JOIN a.produtos p ON a.id =:id")
    List<Produto> findProdutosByIdAnimal(@Param("id") Long id);

    List<Animal> findByDataNascimentoDataBetweenAndEspecieIs(LocalDate inicio, LocalDate fim, EspecieEnum especie);

    List<Animal> findByClienteIdAndNome(Long aLong, String s);

    @Query("SELECT a FROM Animal a WHERE a.unidade.id = :unidadeId ORDER BY a.cliente.id")
    List<Animal>findByUnidadeIdAndClienteId(@Param("unidadeId")Long unidadeId);


    /*List<Animal> animais = new ArrayList<>(Arrays.asList(
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
    }*/


}
