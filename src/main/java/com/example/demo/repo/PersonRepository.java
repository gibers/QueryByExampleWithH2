package com.example.demo.repo;

import com.example.demo.entites.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends CrudRepository<Person, Long>, QueryByExampleExecutor<Person> {


}
