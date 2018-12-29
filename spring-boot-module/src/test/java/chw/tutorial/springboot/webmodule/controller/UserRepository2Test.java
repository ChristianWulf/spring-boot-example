package chw.tutorial.springboot.webmodule.controller;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import javax.naming.Name;

import org.junit.Ignore;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.ldap.DataLdapTest;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.test.context.junit4.SpringRunner;

import chw.tutorial.springboot.ldap.User;
import chw.tutorial.springboot.ldap.UserRepository;

@RunWith(SpringRunner.class)
// @ExtendWith(SpringExtension.class)
// @SpringBootTest()
@DataLdapTest
public class UserRepository2Test {

	@Autowired
	UserRepository userRepository;

	@Test
	@Ignore("unboundid seems to not support creating new users.")
	public void createNewUser() throws Exception {
		Name expectedDn = LdapUtils.newLdapName("uid=christian,ou=people,dc=springframework,dc=org");

		User user = new User();
		user.setDn(LdapUtils.emptyLdapName());
		// user.setDn(expectedDn);
		// user.setDn(LdapUtils.newLdapName("uid=christian,ou=people,dc=springframework,dc=org"));
		user.setUid("christian");
		user.setUsername("Christian Wulf");
//		user.setHashedPassword("wulfspassword");
		user = userRepository.save(user);

		assertThat(user.getDn(), is(expectedDn));
	}

	@Test
	public void findExistingUser() throws Exception {
		User user = userRepository.findByUsername("Bob Hamilton");

		assertThat(user.getDn(), is(LdapUtils.newLdapName("uid=bob,ou=people,dc=springframework,dc=org")));
		assertThat(user.getGroup(), is(User.GROUP));
		assertThat(user.getUid(), is("bob"));
		assertThat(user.getHashedPassword(), is("{noop}bobspassword".getBytes()));
	}

	@Test
	public void findAllUsers() throws Exception {
		List<User> users = userRepository.findAll();

		assertThat(users, hasSize(7));
	}
}
