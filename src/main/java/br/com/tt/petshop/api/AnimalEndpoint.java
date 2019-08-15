package br.com.tt.petshop.api;


import br.com.tt.petshop.dto.AnimalDto;
import br.com.tt.petshop.service.AnimalService;
import io.swagger.annotations.*;
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
@Api(tags ="Animal", description = "Animal Controller")
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
    @ApiOperation("Lista os animais por Cliente e/ou nome")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista de animais retornada com sucesso"),
            @ApiResponse(code = 400, message = "Parametros informados incorretamente")
    })
    public ResponseEntity<List<AnimalDto>> list(
            @ApiParam("Id do cliente para filtro")
            @PathVariable Optional<Long> clienteId,
            @ApiParam("nome do animal")
            @PathVariable Optional<String> nome){
        return ResponseEntity.ok(animalService
                .listar(clienteId, nome)
                .stream()
                .map(c -> mapper.map(c, AnimalDto.class))
                .collect(Collectors.toList()));

    }

}
