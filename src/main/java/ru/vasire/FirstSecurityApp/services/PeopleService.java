package ru.vasire.FirstSecurityApp.services;

import org.springframework.stereotype.Service;
import ru.vasire.FirstSecurityApp.models.Person;
import ru.vasire.FirstSecurityApp.repositories.PeopleRepository;

import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;


    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> isUserExists(String username){
        return peopleRepository.findByUsername(username);
    }
}
