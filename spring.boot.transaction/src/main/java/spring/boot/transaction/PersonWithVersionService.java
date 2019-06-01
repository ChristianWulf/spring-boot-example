package spring.boot.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.transaction.entity.PersonWithVersion;

@Service
public class PersonWithVersionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonWithVersionService.class);

	@Autowired
	PersonWithVersionRepository personRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Transactional
	public PersonWithVersion createPerson(String forename, String surename) {
		PersonWithVersion person = new PersonWithVersion();
		person.setForename(forename);
		person.setSurename(surename);

		person = personRepository.save(person);
		// personRepository.saveAndFlush(entity);
		LOGGER.debug("last line of method");
		return person;
	}
}
