package br.com.tt.petshop.model;

import java.util.Objects;

public class Cliente {
   private Long id = new Long(0);
    private String nome;
    private String cpf;
    private Boolean inadimplente = false;

    public Cliente(){}

    public static Cliente newClienteNome(String nome){
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        return  cliente;
    }

    public static Cliente newClienteNomeCpf(String nome, String cpf){
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        return  cliente;
    }

    public static Cliente newIdClienteNomeCpfInadimplente(Long id, String nome, String cpf, Boolean inadimplente){
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setInadimplente(inadimplente);
        return  cliente;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getInadimplente() {
        return inadimplente;
    }

    public void setInadimplente(Boolean inadimplente) {
        this.inadimplente = inadimplente;
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
