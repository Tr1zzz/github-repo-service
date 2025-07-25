package com.david.github_repo_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Internal DTO for deserializing GitHub repository branches.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record BranchDto(
        String name,
        Commit commit
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Commit(String sha) {}
}
