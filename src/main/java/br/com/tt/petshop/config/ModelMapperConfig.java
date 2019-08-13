package br.com.tt.petshop.config;

import br.com.tt.petshop.dto.ClienteDto;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.model.vo.Cpf;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getBen(){
        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);


        modelMapper
                .createTypeMap(Cliente.class, ClienteDto.class)
                .addMapping(cliente -> cliente.getCpf().getValor(), ClienteDto::setCpf);
                //.addMapping(Cliente::getCpf, (clienteDto, o) -> clienteDto.setCpf(((Cpf)o).getValor()));


       modelMapper
                .createTypeMap(ClienteDto.class, Cliente.class)
                .addMapping(dto -> dto.getCpf(), (cliente, o ) -> cliente.getCpf().setValor((String)o));
                //.addMapping(dto -> new Cpf(dto.getCpf()), Cliente::setCpf);



       /* PropertyMap< ClienteDto, Cliente > clienteMap = new PropertyMap< ClienteDto, Cliente>() {
            protected void configure() {
                map().getCpf().setValor(source.getCpf());
            }
        };
        modelMapper.addMappings(clienteMap);*/

        return modelMapper;
    }
}
