package spring.boot.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.transaction.entity.PersonWithVersion;

public interface PersonWithVersionRepository extends JpaRepository<PersonWithVersion, Long> {

}
