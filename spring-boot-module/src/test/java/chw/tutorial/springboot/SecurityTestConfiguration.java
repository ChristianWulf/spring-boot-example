package chw.tutorial.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
// @ImportResource("classpath:/WEB-INF/spring/security.xml")
// @Order(99)
// @EnableLdapRepositories
public class SecurityTestConfiguration extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityTestConfiguration.class);

	// @Autowired
	// private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// @formatter:off
//		auth.inMemoryAuthentication()
//			.withUser("wulfchr")
//				.password(passwordEncoder.encode("password4"))
//				.roles("USER")
//				.and()
//			.withUser("admin")
//				.password(passwordEncoder.encode("admin"))
//				.roles("USER", "ADMIN")
//				.and()
//			.withUser("bob")
//				.password("{noop}bobspassword")
//				.roles("USER", "ADMIN");

		auth.ldapAuthentication()
			.userDnPatterns("uid={0},ou=people")
//			.userSearchBase("ou=people")
//            .userSearchFilter("uid={0}")
			.groupSearchBase("ou=groups")
//			.groupSearchFilter("uniqueMember={0}")
			.contextSource()	// reads spring.ldap.embedded from .properties-file
//				.managerDn("ui=bob,ou=people")
//				.managerPassword("bobspassword")
				.url("ldap://localhost:33389/dc=springframework,dc=org")
//				.ldif("classpath:bootstrap.ldif")
//				.port(44444)
				.and()
			.passwordCompare()
//				.passwordEncoder(NoOpPasswordEncoder.getInstance())
				.passwordEncoder(passwordEncoder)
				.passwordAttribute("userPassword")
			.and();

		// auth.jdbcAuthentication()
		// @formatter:on
		LOGGER.info("Security configuration loaded.");
	}

	// @Bean
	// @Override
	// public UserDetailsService userDetailsService() {
//		// @formatter:off
//        UserDetails user = User
//        		.withUsername("wulfchr")
//                .password(passwordEncoder.encode("password3"))
//                .roles("USER")
//                .build();
//     // @formatter:on
	//
	// return new InMemoryUserDetailsManager(user);
	// }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
//		http.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and();
		http.authorizeRequests()
			.anyRequest().authenticated()
			.and()
		.csrf().disable()
//		.cors().disable()
		.httpBasic();
		// @formatter:on
	}

	// @Bean
	// LdapContextSource ldapContextSource() {
	// LdapContextSource ldapContextSource = new LdapContextSource();
	// ldapContextSource.setAnonymousReadOnly(true);
	//
	// ldapContextSource.afterPropertiesSet();
	// return ldapContextSource;
	// }
}