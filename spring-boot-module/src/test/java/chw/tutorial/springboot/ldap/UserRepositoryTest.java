package chw.tutorial.springboot.ldap;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import javax.naming.Name;

import org.junit.Test;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
// @ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Test
	@DirtiesContext
	public void createNewUser() throws Exception {
		Name expectedDn = LdapUtils.newLdapName("uid=christian,ou=people");

		User user = new User();
		// user.setDn(LdapUtils.emptyLdapName());
		// user.setDn(expectedDn);
		user.setUid("christian");
		user.setUsername("Christian Wulf");
		user.setHashedPassword("wulfspassword");
		user = userRepository.save(user);

		assertThat(user.getDn(), is(expectedDn));
	}

	@Test
	public void findExistingUser() throws Exception {
		User user = userRepository.findByUsername("Bob Hamilton");

		assertThat(user.getDn(), is(LdapUtils.newLdapName("uid=bob,ou=people")));
		assertThat(user.getGroup(), is(User.GROUP));
		assertThat(user.getUid(), is("bob"));
	}

	@Test
	public void findAllUsers() throws Exception {
		List<User> users = userRepository.findAll();

		assertThat(users, hasSize(7));
	}
}
