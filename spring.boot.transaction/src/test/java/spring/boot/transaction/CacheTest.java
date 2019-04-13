package spring.boot.transaction;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import spring.boot.transaction.entity.Person;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CacheTest {

	@Autowired
	PersonService personService;

	@Test
	void testName() throws Exception {
		String forename = "Christian";
		String surename = "Wulf";

		// 1
		Person person = personService.createPerson(forename, surename);

		// 2
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				personService.updatePerson(person.getId(), "Christina", "Klassen");
			}
		});
		thread.start();
		thread.join(1000);

		// 3
//		List<Person> persons = personService.findByForenameAndSurename(forename, surename);
		List<Person> persons = personService.findAll();

		assertThat(persons.get(0).getForename(), is("Christina"));
		assertThat(persons, hasSize(1));
	}
}
