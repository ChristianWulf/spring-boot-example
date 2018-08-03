package chw.tutorial.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//Initializes Spring Boot Auto Configuration and Spring application context
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableWebSecurity
@ImportResource("classpath:/WEB-INF/spring/security.xml")
@EnableLdapRepositories
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		// SpringApplication.run(Application.class, args);
		configureApplication(new SpringApplicationBuilder()).run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return configureApplication(builder);
	}

	private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

//	@Bean
//	ContextSource contextSource() {
//		LdapContextSource ldapContextSource = new LdapContextSource();
//		ldapContextSource.setUrl("ldap://127.0.0.1:33389/dc=springframework,dc=org");
//
//		return ldapContextSource;
//	}

//	@Bean
//	LdapTemplate ldapTemplate(ContextSource contextSource) {
//		return new LdapTemplate(contextSource);
//	}
}
