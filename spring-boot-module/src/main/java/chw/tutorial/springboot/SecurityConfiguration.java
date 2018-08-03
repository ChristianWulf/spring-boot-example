package chw.tutorial.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
//@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off
		auth.inMemoryAuthentication()
			.withUser("wulfchr")
				.password(passwordEncoder.encode("password4"))
				.roles("USER")
				.and()
			.withUser("admin")
				.password(passwordEncoder.encode("admin"))
				.roles("USER", "ADMIN");
		// @formatter:on

		// auth.ldapAuthentication()

		// auth.jdbcAuthentication()
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