package br.com.tt.petshop.model;

public class Cliente {
    private String nome;
    private String cpf;

    private Cliente(){}

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
}
