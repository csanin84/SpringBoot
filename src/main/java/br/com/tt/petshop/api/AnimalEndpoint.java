package br.com.tt.petshop.api;


import br.com.tt.petshop.dto.AnimalDto;
import br.com.tt.petshop.service.AnimalService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animais")
public class AnimalEndpoint {
    private final AnimalService animalService;
    private final ModelMapper mapper;

    public AnimalEndpoint(AnimalService animalService, ModelMapper mapper) {
        this.animalService = animalService;
        this.mapper = mapper;
    }
    /*
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnimalDto>> findAll(@PathVariable Long id){
        return ResponseEntity.ok(animalService
                .listar(id)
                .stream()
                .map(c -> mapper.map(c, AnimalDto.class))
                .collect(Collectors.toList()));

    }
    */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnimalDto>> list(
            @PathVariable Optional<Long> clienteId,
            @PathVariable Optional<String> nome){
        return ResponseEntity.ok(animalService
                .listar(clienteId, nome)
                .stream()
                .map(c -> mapper.map(c, AnimalDto.class))
                .collect(Collectors.toList()));

    }

}
