package spring.boot.transaction.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LastEventIdRepository extends JpaRepository<LastEventId, Long>, CustomLastEventIdRepository {

	@Transactional
	@Modifying
	@Query("UPDATE LastEventId SET last_event_id=:lastEventId where id=:id and version=:version")
	int updateLastEventId(Long id, String lastEventId, Long version);

//	@Modifying
//	@Query("UPDATE last_event_id SET last_event_id=:lastEventId where id=:id and version=:version")
//	LastEventId updateLastEventId(LastEventId lastEventId);
}
