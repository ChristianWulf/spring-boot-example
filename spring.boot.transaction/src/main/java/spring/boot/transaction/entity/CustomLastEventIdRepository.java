package spring.boot.transaction.entity;

public interface CustomLastEventIdRepository {

	void persist(LastEventId lastEventId);
}
