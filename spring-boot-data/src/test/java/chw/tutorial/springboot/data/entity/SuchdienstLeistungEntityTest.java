package chw.tutorial.springboot.data.entity;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SuchdienstLeistungEntityTest {

	// @Autowired
	// EntityManager entityManager;

	@Autowired
	SuchdienstLeistungRepository repository;

	@Test
	void testName() throws Exception {
		Instant now = Instant.now();
		LocalDateTime.now();
		System.out.println("now: " + now.getNano());

		Timestamp nowTimestamp = Timestamp.from(now);
		System.out.println("nowTimestamp: " + nowTimestamp.getNanos());

		SuchdienstLeistungEntity entity = new SuchdienstLeistungEntity();
		entity.setLbId("neue LB");
		// entity.setModifiedDate(nowTimestamp);

		// entity = entityManager.persistAndFlush(entity);
		repository.save(entity);

		List<SuchdienstLeistungEntity> leistungen = repository.findAll();
		SuchdienstLeistungEntity leistung = leistungen.get(0);

		Timestamp modifiedDate = leistung.getModifiedDate();

		Thread.sleep(200);

		// repository.touch(leistung.getId()); // does not update modifiedDate
		// repository.flush();
		repository.touchDate(leistung.getId());

		// entityManager.flush();

		// entity.setLbId("aaaa");
		// repository.saveAndFlush(entity);

		Optional<SuchdienstLeistungEntity> leistungResult = repository.findById(leistung.getId());
		leistung = leistungResult.get();

		Timestamp newModifiedDate = leistung.getModifiedDate();

		System.out.println("newModifiedDate: " + newModifiedDate.getNanos());
		System.out.println("modifiedDate: " + modifiedDate.getNanos());
		System.out.println("leistung.getLbId(): " + leistung.getLbId());

		// assertThat(leistung.getLbId(), is("xyz"));
		assertThat(newModifiedDate.toInstant(), is(not(modifiedDate.toInstant())));
	}
}
