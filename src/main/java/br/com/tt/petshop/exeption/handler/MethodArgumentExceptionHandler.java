package br.com.tt.petshop.exeption.handler;

import br.com.tt.petshop.exeption.dto.ApiErroDetailDto;
import org.springframework.web.bind.MethodArgumentNotValidException;
import br.com.tt.petshop.exeption.dto.ApiErroDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@ControllerAdvice
public class MethodArgumentExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErroDto handle(MethodArgumentNotValidException e){
        List<ApiErroDetailDto> erros =  e
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        new ApiErroDetailDto(String.format("%s: %s",
                        error.getField(),
                        error.getDefaultMessage())))
                .collect(Collectors.toList());

        return new ApiErroDto("Erro de validacao", "Alguns campos não satifacem as regras de negocio", erros);
    }

}
