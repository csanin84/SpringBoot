package br.com.tt.petshop.api;

import br.com.tt.petshop.model.Unidade;
import br.com.tt.petshop.service.UnidadeService;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

//@Controller
@RestController
@RequestMapping(value = "/unidades")
public class UnidadEndpoind {
    private final UnidadeService unidadeService;

    public UnidadEndpoind(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    //  /unidades
    //@ResponseBody //si uso anotación @Controller
    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Unidade>findAll(){
        return unidadeService.findAll();
    }

//  /unidades/{id}
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Unidade> findById(@PathVariable Long id){
        Optional<Unidade> unidadeOptional = unidadeService.findById(id);
        if(unidadeOptional.isPresent())
            return ResponseEntity.ok(unidadeOptional.get());

        return ResponseEntity.notFound().build();
    }

//  /unidades
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Unidade unidade){
        Unidade unidadeCriada = unidadeService.create(unidade);
        URI uri = URI.create(String.format("/unidades/%d",unidadeCriada.getId()));
        return ResponseEntity.created(uri).build();
    }

//  /unidades/{id}
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody Unidade unidade, @PathVariable Long id){
        unidade.setId(id);
        unidadeService.update(unidade);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        unidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
