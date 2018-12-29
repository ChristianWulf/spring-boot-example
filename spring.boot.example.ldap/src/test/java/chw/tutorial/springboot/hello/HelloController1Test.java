package chw.tutorial.springboot.hello;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloController1Test {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getHelloWithValidLogin() throws Exception {
		// @formatter:off
		mvc.perform(get("/").with(httpBasic("bob", "bobspassword")).with(csrf()))
			.andExpect(status().isOk())
			.andExpect(content().string(equalTo("Greetings from Spring Boot!")));
		// @formatter:on
	}

	@Test
	public void getHelloWithInvalidPassword() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/")
				.accept(MediaType.APPLICATION_JSON);

		mvc.perform(requestBuilder.with(httpBasic("bob", "wrong password"))).andExpect(status().isUnauthorized());
	}
}
