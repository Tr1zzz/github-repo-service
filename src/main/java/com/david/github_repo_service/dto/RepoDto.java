package com.david.github_repo_service.dto;

import java.util.List;

public record RepoDto(
        String name,
        String ownerLogin,
        List<BranchDto> branches
) {}