package br.com.tt.petshop.api;

import br.com.tt.petshop.dto.ClienteDto;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteEndpoind {
    private final ClienteService clienteService;
    private final ModelMapper mapper;

    public ClienteEndpoind(ClienteService clienteService, ModelMapper mapper) {
        this.clienteService = clienteService;
        this.mapper = mapper;
    }

    // /clientes
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClienteDto>> findAll(){
        return ResponseEntity.ok(clienteService
                .listar()
                .stream()
                .map(c -> mapper.map(c, ClienteDto.class))
                .collect(Collectors.toList()));
    }

    // /clientes/{id}
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteDto> findById(@PathVariable Long id){
        Optional<Cliente> clienteOptional = clienteService.findId(id);
        if(clienteOptional.isPresent())
            return ResponseEntity.ok(mapper.map(clienteOptional.get(), ClienteDto.class));
        return ResponseEntity.notFound().build();
    }

    // /clientes
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody ClienteDto clienteDto){
        System.out.println(clienteDto.getCpf());
        Cliente clienteCriado = clienteService.adicionar(mapper.map(clienteDto, Cliente.class));
        URI uri = URI.create( String.format("/clientes/%d", clienteCriado.getId()) );
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
