package br.com.tt.petshop.exeption.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErroDto {
    private String key;
    private String message;
    private LocalDateTime time;
    private List<ApiErroDetailDto> details;

    public ApiErroDto() {
    }

    public ApiErroDto(String key, String message, List<ApiErroDetailDto> details) {
        this.key = key;
        this.message = message;
        this.details = details;
        this.time = LocalDateTime.now();
    }

    public ApiErroDto(String key, String message) {
        this.key = key;
        this.message = message;
        this.time = LocalDateTime.now();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<ApiErroDetailDto> getDetails() {
        return details;
    }
}
