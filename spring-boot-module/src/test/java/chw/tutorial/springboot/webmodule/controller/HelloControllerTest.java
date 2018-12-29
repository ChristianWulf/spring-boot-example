package chw.tutorial.springboot.webmodule.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import chw.tutorial.springboot.SecurityTestConfiguration;

@RunWith(SpringRunner.class)
// @SpringBootTest
// @AutoConfigureMockMvc
@WebMvcTest(value = HelloController.class)
@Import(SecurityTestConfiguration.class)
//@AutoConfigureDataLdap
public class HelloControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private WebApplicationContext wac;
	//
	// @Autowired
	// private FilterChainProxy springSecurityFilterChain;

	// @Before
	// public void setupMockMvc() {
	// // this.mvc =
	// //
	// MockMvcBuilders.webAppContextSetup(this.wac).addFilters(this.springSecurityFilterChain).build();
	// this.mvc =
	// MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
	// }

	@Test
	// @WithMockUser(username = "bob") // password is not evaluated
	public void getHelloWithValidLogin() throws Exception {
		// MockHttpServletRequestBuilder requestBuilder =
		// MockMvcRequestBuilders.get("/")
		// .accept(MediaType.APPLICATION_JSON);

		// @formatter:off
		mvc.perform(get("/").with(httpBasic("bob", "bobspassword")).with(csrf()))
//		mvc.perform(get("/").with(httpBasic("ben", "benspassword")).with(csrf()))
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
