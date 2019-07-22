package br.com.tt.petshop.model;

import java.util.Objects;

public class Cliente {
    private static Long contador = new Long(0);
    private Long id = new Long(0);
    private String nome;
    private String cpf;

    public Cliente(){contador++;}

    public static Cliente newIdClienteNome(String nome){
        Cliente cliente = new Cliente();
        cliente.setId(contador);
        cliente.setNome(nome);
        return  cliente;
    }

    public static Cliente newIdClienteNomeCpf(String nome, String cpf){
        Cliente cliente = new Cliente();
        cliente.setId(contador);
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        return  cliente;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
