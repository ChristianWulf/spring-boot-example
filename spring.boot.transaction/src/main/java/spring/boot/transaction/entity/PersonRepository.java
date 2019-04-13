package spring.boot.transaction.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	List<Person> findByForenameAndSurename(String forename, String surename);

}
