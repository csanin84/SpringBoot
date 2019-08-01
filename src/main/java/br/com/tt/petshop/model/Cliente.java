package br.com.tt.petshop.model;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "TB_CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME_CLIENTE", length = 20)
    private String nome;

    @Column(name = "CPF_CLIENTE", length = 14)
    private String cpf;

    @Column(name = "INADIMPLENTE")
    private Boolean inadimplente = false;

    public Cliente(){}

    public Cliente(String nome, String cpf, Boolean inadimplente) {

        this.nome = nome;
        this.cpf = cpf;
        this.inadimplente = inadimplente;
    }

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

    public static Cliente newIdClienteNomeCpfInadimplente(String nome, String cpf, Boolean inadimplente){
        Cliente cliente = new Cliente();
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
