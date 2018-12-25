package chw.tutorial.springboot.ldap;

import java.util.List;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends LdapRepository<User> {

	User findByUsername(String username);

	List<User> findByUsernameLikeIgnoreCase(String username);
	
	@Override
	List<User> findAll();
}