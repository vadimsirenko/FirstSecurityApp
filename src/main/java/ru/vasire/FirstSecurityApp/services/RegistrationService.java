package ru.vasire.FirstSecurityApp.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vasire.FirstSecurityApp.dto.PersonDTO;
import ru.vasire.FirstSecurityApp.models.Person;
import ru.vasire.FirstSecurityApp.repositories.PeopleRepository;

@Service
public class RegistrationService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");

        peopleRepository.save(person);
    }


}
