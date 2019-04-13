package chw.tutorial.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
// @ImportResource("classpath:/WEB-INF/spring/security.xml")
// @EnableLdapRepositories
@Profile("default")
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);

	// @Autowired
	// private UserDetailsService userDetailsService;

	// private final PasswordEncoder passwordEncoder =
	// PasswordEncoderFactories.createDelegatingPasswordEncoder();

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off
//		auth.inMemoryAuthentication()
//			.withUser("wulfchr")
//				.password(passwordEncoder.encode("password4"))
//				.roles("USER")
//				.and()
//			.withUser("admin")
//				.password(passwordEncoder.encode("admin"))
//				.roles("USER", "ADMIN");

		auth.ldapAuthentication()
			.userDnPatterns("uid={0},ou=people")
			.groupSearchBase("ou=groups")
			.contextSource()	// starts an LDAP server if url is not provided
				.url("ldap://localhost:33389/dc=springframework,dc=org")
//				.ldif("classpath:bootstrap-spring-security.ldif")
				// automatically adds the entry indicated by .root()
//				.port(44444)
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
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
		// @formatter:on
	}
}