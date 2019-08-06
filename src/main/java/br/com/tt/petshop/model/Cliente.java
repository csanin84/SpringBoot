package br.com.tt.petshop.model;

import br.com.tt.petshop.model.vo.Cpf;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

    @Embedded
    private Cpf cpf;

    @Column(name = "INADIMPLENTE")
    private Boolean inadimplente = false;

    //fetch: manera de llenar la lista, cascade: manera de actualizar o borrar la lista en BD
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    List<Animal> animais = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name="ID_UNIDADE")
    private Unidade unidade;

    public Cliente(){}

    public static  Cliente newClienteById(Long id){
        Cliente cliente = new ClienteNull();
        cliente.setId(id);
        return cliente;
    }

    public Cliente(String nome, String cpf, Boolean inadimplente) {

        this.nome = nome;
        this.cpf = new Cpf(cpf);
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
        cliente.setCpf(new Cpf(cpf));
        return  cliente;
    }

    public static Cliente newIdClienteNomeCpfInadimplente(String nome, String cpf, Boolean inadimplente){
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(new Cpf(cpf));
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

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
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
