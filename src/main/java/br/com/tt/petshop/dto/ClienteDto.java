package br.com.tt.petshop.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ClienteDto {

    private Long id;
    @NotBlank(message = "O  nome deve ser informado")
    @Size(min =2, max = 100, message = "O nome deve conter de 2 a 11 caracteres")
    private String nome;

    @Pattern(regexp = "^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}", message = "Cpf inválido")
    private String cpf;

    private List<AnimalDto> animais = new ArrayList<>();

    public ClienteDto() {
    }

    public ClienteDto(Long id,  String nome,  String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<AnimalDto> getAnimais() {
        return animais;
    }

    public void setAnimais(List<AnimalDto> animais) {
        this.animais = animais;
    }
}
