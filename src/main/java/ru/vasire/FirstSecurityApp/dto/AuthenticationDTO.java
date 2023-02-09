package ru.vasire.FirstSecurityApp.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class AuthenticationDTO {

    @NotEmpty(message = "Поле Имя обязательное")
    @Size(min = 1, max = 100, message = "Имя должно быть от 2 до 100 символов")
    private String username;
    @NotEmpty(message = "Поле Пароль обязательное")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
