package spring.boot.transaction.entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

class LastEventIdRepositoryImpl implements CustomLastEventIdRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void persist(LastEventId lastEventId) {
		entityManager.persist(lastEventId);
	}

}
