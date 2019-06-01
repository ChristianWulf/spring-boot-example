package spring.boot.transaction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.transaction.entity.Person;
import spring.boot.transaction.entity.PersonRepository;

@Service
public class PersonService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

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
		LOGGER.debug("last line of method");
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

	public List<Person> selectPersons() {
		System.out.println("selectPersons:");
		List<Person> persons = jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper(Person.class));
		return persons;
	}

	public List<Person> findAll() {
		List<Person> persons = personRepository.findAll();
		return persons;
	}

	@Transactional
	public List<Person> performMultipleTransactions(String forename, String surename) {
		// 1
		System.out.println("create person:");
		Person person = createPerson(forename, surename);

		System.out.println("flush:");
		// flush so that the following update statement finds the entry with the id in
		// the database
		personRepository.flush();

		// 2
		System.out.println("updatePerson:");
		int numUpdatedRows = updatePerson(person.getId(), "Christina", "Klassen");
		System.out.println("numUpdatedRows: " + numUpdatedRows);

		System.out.println("findAll 1:");
		// 3
		// finds the entry in the cache and misses the new name "Christina Klassen" =>
		// wrong; calls the DB again
		List<Person> persons = findAll();
		// List<Person> persons = personRepository.findAll();

		// 4
		System.out.println("findAll 2:");
		persons = findAll();
		// persons = personRepository.findAll();
		// List<Person> persons = new ArrayList<>();

		return persons;
	}

	public void deleteAll() {
		System.out.println("deleteAll:");
		personRepository.deleteAll();
	}
}
