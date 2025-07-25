package com.david.github_repo_service;

import com.david.github_repo_service.dto.RepoDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test (happy path) – exercises the full stack against the real GitHub API.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void shouldReturnNonForkReposWithBranchesForExistingUser() {
		// Use a well‑known existing GitHub user
		ResponseEntity<RepoDto[]> response =
				restTemplate.getForEntity("/users/octocat/repos", RepoDto[].class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		RepoDto[] body = response.getBody();
		assertThat(body).isNotNull();
		assertThat(body.length).isGreaterThan(0);

		assertThat(body).allSatisfy(repo -> {
			assertThat(repo.name()).isNotBlank();
			assertThat(repo.ownerLogin()).isEqualTo("octocat");
			assertThat(repo.branches()).isNotNull();
			repo.branches().forEach(branch -> {
				assertThat(branch.name()).isNotBlank();
				assertThat(branch.commit()).isNotNull();
				assertThat(branch.commit().sha()).isNotBlank();
			});
		});
	}
}