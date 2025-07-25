package com.david.github_repo_service.dto;

public record ErrorResponse(
        int status,
        String message
) {}