package br.com.tt.petshop.model;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.vo.DataNascimento;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TB_ANIMAL")
public class Animal {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "codigo", insertable = false)
    private Long id;
    private String nome;
    @Embedded
    private DataNascimento dataNascimento;

    @Enumerated(EnumType.STRING)
    private EspecieEnum especie;

    //só lectura
    //@Column(name = "CLIENT_ID", updatable = false, insertable = false)
    //private Long clientId;

    @ManyToOne
    @JoinColumn(name="CLIENT_ID")
    private Cliente cliente;

    public Animal( String nome, LocalDate dataNascimento, EspecieEnum especie, Cliente cliente) {
        //this.id = id;
        this.nome = nome;
        this.dataNascimento = new DataNascimento(dataNascimento);
        this.especie = especie;
        this.cliente = new Cliente();
    }

    public Animal() {
        this.dataNascimento = new DataNascimento();
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

    public DataNascimento getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(DataNascimento dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public EspecieEnum getEspecie() {
        return especie;
    }

    public void setEspecie(EspecieEnum especie) {
        this.especie = especie;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", especie=" + especie +
                ", cliente=" + cliente.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id.equals(animal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
