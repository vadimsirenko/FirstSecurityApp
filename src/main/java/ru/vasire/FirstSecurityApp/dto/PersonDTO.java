package ru.vasire.FirstSecurityApp.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class PersonDTO {

    @NotEmpty(message = "Поле Имя обязательное")
    @Size(min = 1, max = 100, message = "Имя должно быть от 2 до 100 символов")
    private String username;
    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    private int yearOfBirth;
    @NotEmpty(message = "Поле Пароль обязательное")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
