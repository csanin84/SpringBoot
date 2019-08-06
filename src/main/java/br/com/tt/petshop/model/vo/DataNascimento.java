package br.com.tt.petshop.model.vo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class DataNascimento {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "data_nascimento")
    private LocalDate data;

    public DataNascimento() {  }

    public DataNascimento(LocalDate data) {
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isValid(){
        if(Objects.isNull(data))
            return false;
        else if(data.isAfter(LocalDate.now()))
            return false;

        return true;
    }
}//fin class DataNascimento
