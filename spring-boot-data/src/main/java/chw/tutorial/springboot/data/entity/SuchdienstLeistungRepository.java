package chw.tutorial.springboot.data.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SuchdienstLeistungRepository extends JpaRepository<SuchdienstLeistungEntity, Long> {

	@Query("UPDATE SuchdienstLeistungEntity e SET e.modifiedDate=CURRENT_TIMESTAMP() where e.id=:id")
	@Modifying(clearAutomatically = true)
	@Transactional
	void touchDate(@Param("id") long id);

	@Query("UPDATE SuchdienstLeistungEntity e SET e.lbId='xyz' where e.id=:id")
	// @Query("UPDATE SuchdienstLeistungEntity e SET e.id=id where e.id=:id")
	@Modifying(clearAutomatically = true)
	@Transactional
	void touch(@Param("id") long id);
}
