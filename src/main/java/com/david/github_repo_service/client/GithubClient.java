package com.david.github_repo_service.client;

import com.david.github_repo_service.dto.BranchDto;
import com.david.github_repo_service.dto.RawRepo;
import com.david.github_repo_service.exception.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Component
public class GithubClient {

    private static final String BASE = "https://api.github.com";
    private final RestTemplate restTemplate = new RestTemplate();

    public RawRepo[] getUserRepos(String username) {
        try {
            RawRepo[] repos = restTemplate.getForObject(
                    BASE + "/users/{user}/repos", RawRepo[].class, username);
            return repos != null ? repos : new RawRepo[0];
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UserNotFoundException("User not found: " + username);
            }
            throw e;
        }
    }

    public BranchDto[] getBranches(String owner, String repo) {
        try {
            BranchDto[] branches = restTemplate.getForObject(
                    BASE + "/repos/{owner}/{repo}/branches", BranchDto[].class, owner, repo);
            return branches != null ? branches : new BranchDto[0];
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UserNotFoundException("User or repository not found: " + owner + "/" + repo);
            }
            throw e;
        }
    }
}