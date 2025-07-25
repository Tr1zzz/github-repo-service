package com.david.github_repo_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Internal DTO for deserializing the GitHub API response from /users/{user}/repos
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record RawRepo(
        String name,
        Owner owner,
        boolean fork
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Owner(String login) {}
}
