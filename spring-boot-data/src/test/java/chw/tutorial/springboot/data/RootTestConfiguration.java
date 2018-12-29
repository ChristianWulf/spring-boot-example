package chw.tutorial.springboot.data;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableJpaAuditing // (dateTimeProviderRef = "microSecondDateTimeProvider")
public class RootTestConfiguration {

	// @Bean
	// DateTimeProvider microSecondDateTimeProvider() {
	// return new DateTimeProvider() {
	//
	// @Override
	// public Optional<TemporalAccessor> getNow() {
	// return Optional.of(LocalDateTime.now());
	// }
	// };
	// }
}
