package ru.vasire.FirstSecurityApp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vasire.FirstSecurityApp.models.Person;
import ru.vasire.FirstSecurityApp.services.PeopleService;
@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person)target;
        if(peopleService.isUserExists(person.getUsername()).isPresent()){
            errors.rejectValue("username","","Пользователь с таким именем уже существует");
        }
    }
}
