package br.com.tt.petshop.api;

import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteEndpoind {
    private final ClienteService clienteService;

    public ClienteEndpoind(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // /clientes
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cliente> findAll(){
        return clienteService.listar();
    }

    // /clientes/{id}
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Optional<Cliente> cliente = clienteService.findId(id);
        if(cliente.isPresent())
            return ResponseEntity.ok(cliente.get());
        return ResponseEntity.notFound().build();
    }

    // /clientes
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Cliente cliente){
        Cliente clienteCriado = clienteService.adicionar(cliente);
        URI uri = URI.create(String.format("/clientes/%d",clienteCriado.getId()));
        return ResponseEntity.created(uri).build();
    }

    // /clientes/{id}
   @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity update(@RequestBody Cliente cliente, @PathVariable Long id){
        cliente.setId(id);
        clienteService.adicionar(cliente);
        return ResponseEntity.noContent().build();
   }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
