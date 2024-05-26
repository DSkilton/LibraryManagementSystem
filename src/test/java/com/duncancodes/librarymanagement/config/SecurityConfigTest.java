package com.duncancodes.librarymanagement.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityConfigTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldPermitAllForRoot() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void shouldRedirectForUserPageWithoutAuthentication() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/user/profile", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND); // Redirect to login
	}

	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void shouldAllowAccessToUserPageWithAuthentication() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/user/profile", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void shouldAllowAccessToAdminPageWithAdminRole() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/admin/dashboard", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void shouldDenyAccessToAdminPageWithUserRole() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/admin/dashboard", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
	}
}
