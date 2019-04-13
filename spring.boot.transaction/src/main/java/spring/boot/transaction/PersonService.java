package spring.boot.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.transaction.entity.Person;
import spring.boot.transaction.entity.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Transactional
	public Person createPerson(String forename, String surename) {
		Person person = new Person();
		person.setForename(forename);
		person.setSurename(surename);

		person = personRepository.save(person);
		// personRepository.saveAndFlush(entity);
		return person;
	}

	@Transactional
	public List<Person> findByForenameAndSurename(String forename, String surename) {
		List<Person> persons = personRepository.findByForenameAndSurename(forename, surename);

		return persons;
	}

	public int updatePerson(Long id, String forename, String surename) {
		int numUpdatedRows = jdbcTemplate.update("UPDATE person SET forename=?, surename=? WHERE id=?", forename,
				surename, id);
		return numUpdatedRows;
	}

	public List<Person> findAll() {
		List<Person> persons = personRepository.findAll();
		return persons;
	}
}
