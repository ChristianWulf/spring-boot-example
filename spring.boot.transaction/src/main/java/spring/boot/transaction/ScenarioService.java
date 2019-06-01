package spring.boot.transaction;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.transaction.entity.LastEventId;
import spring.boot.transaction.entity.LastEventIdRepository;
import spring.boot.transaction.entity.Person;
import spring.boot.transaction.entity.PersonRepository;
import spring.boot.transaction.entity.PersonWithVersion;

@Service
public class ScenarioService {

	@Autowired
	PersonService personService;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	PersonWithVersionRepository personWithVersionRepository;

	@Autowired
	LastEventIdRepository lastEventIdRepository;

	@Transactional
	public LastEventId performScenario(LastEventId lastEventId) { // detached entity
		Person person = new Person();
		person.setForename(Long.toString(new Random().nextLong()));
		person.setSurename("Test");

		personRepository.save(person);

		String newLastEventId = Integer.toHexString(person.hashCode());
		// getOne and findById work correctly
		lastEventId = lastEventIdRepository.getOne(lastEventId.getId()); // attach entity to session
		// Optional<LastEventId> optionalLastEventId =
		// lastEventIdRepository.findById(lastEventId.getId());
		// Optional<LastEventId> optionalLastEventId =
		// lastEventIdRepository.findOne(lastEventId);
		// lastEventId = optionalLastEventId.get();
		lastEventId.setLastEventId(newLastEventId);
		// lastEventId = lastEventIdRepository.updateLastEventId(lastEventId);
		// lastEventIdRepository.updateLastEventId(lastEventId.getId(), newLastEventId,
		// lastEventId.getVersion());
		// lastEventIdRepository.persist(lastEventId);
		// lastEventId = lastEventIdRepository.save(lastEventId);
		return lastEventId;
	}

	@Transactional
	public Person updatePerson(Person person) {
		System.out.println("Surename: " + person.getSurename());

		person.setSurename("Wulf");
		// person = personRepository.save(person); // makes changes visible to next
		// transactions
		// personRepository.flush(); // does not make changes visible to next
		// transactions
		return person;
	}

	// @Transactional
	public PersonWithVersion updatePerson(PersonWithVersion person) {
		System.out.println("Surename: " + person.getSurename());

		person.setSurename("Wulf");
		// person = personWithVersionRepository.save(person); // makes changes visible
		// to next transactions
		// personRepository.flush(); // does not make changes visible to next
		// transactions
		return person;
	}
}
