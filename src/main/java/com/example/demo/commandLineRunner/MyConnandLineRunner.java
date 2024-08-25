package com.example.demo.commandLineRunner;

import com.example.demo.entites.Person;
import com.example.demo.repo.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@Slf4j
@Component
public class MyConnandLineRunner implements CommandLineRunner {

    private final Person person = new Person();

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        createAndInsertInDBPerson();

        Iterable<Person> all = personRepository.findAll();
        displayThePerson(all);

        executeExempleQuery();

    }

    private void createAndInsertInDBPerson() {
        this.person.setAddress("address");
        person.setEmail("email");
        person.setName("name");
        person.setSurname("surname");
        personRepository.save(this.person);
    }

    private static void displayThePerson(Iterable<Person> all) {
        List<Person> ids = StreamSupport.stream(all.spliterator(), false)
//                .map(Person::getId)
                .toList();

        log.debug(" DEBUG list des ids => {}", ids);
        log.info(" list des ids => {}", ids);
    }

    private void executeExempleQuery() {
        Person personQuery = new Person();
//        personQuery.setName("name");
//        personQuery.setSurname("surname");
//        personQuery.setEmail("email");
//        personQuery.setAddress("address");
//        personQuery.setId(1);

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("name", endsWith())
                .withIgnorePaths("id", "surname", "address");

        Example<Person> ex = Example.of(personQuery, matcher);
        Optional<Person> all = this.personRepository.findBy(ex, q -> q.sortBy(Sort.by("id")).first());

        if (all.isPresent()) {
            Person person1 = all.get();
            log.info("person1 trouvé => {}", person1);
        }

//        displayThePerson(all);
    }

}
