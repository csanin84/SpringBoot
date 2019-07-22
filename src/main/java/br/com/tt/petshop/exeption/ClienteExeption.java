package br.com.tt.petshop.exeption;

public class ClienteExeption extends RuntimeException{
    public ClienteExeption(String msg){
        super(msg);
    }
}
