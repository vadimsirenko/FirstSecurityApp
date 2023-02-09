package ru.vasire.FirstSecurityApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vasire.FirstSecurityApp.dto.AuthenticationDTO;
import ru.vasire.FirstSecurityApp.dto.PersonDTO;
import ru.vasire.FirstSecurityApp.models.Person;
import ru.vasire.FirstSecurityApp.security.JWTUtil;
import ru.vasire.FirstSecurityApp.services.RegistrationService;
import ru.vasire.FirstSecurityApp.util.PersonValidator;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService registrationService, JWTUtil jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }
/*
    @GetMapping("/login")
    public String login()
    {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") PersonDTO personDTO){
        return "/auth/registration";
    }
*/
    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid PersonDTO personDTO
            , BindingResult bindingResult){
        Person person = personDtoToPerson(personDTO);

        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors())
            return Map.of("message", "ошибка");

        registrationService.register(person);
        return Map.of("token",jwtUtil.generateToken(person.getUsername()));
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody @Valid AuthenticationDTO authenticationDTO,
                                            BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return Map.of("message", "ошибка");
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());
        try{
            authenticationManager.authenticate(auth);
        }catch (BadCredentialsException exception){
            return Map.of("error", exception.getMessage());
        }
        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("token", token);
    }
    private Person personDtoToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }
    private PersonDTO personToPersonDto(Person person){
        return modelMapper.map(person, PersonDTO.class);
    }

}
