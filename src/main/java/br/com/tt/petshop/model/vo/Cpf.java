package br.com.tt.petshop.model.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Cpf {

    @Column(name = "CPF_CLIENTE")
    private String valor;

    public Cpf() {}

    public Cpf(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public boolean isValid(){
        return valor != null && valor.replaceAll("\\D", "").length() == 11;
    }

}//fin class Cpf
