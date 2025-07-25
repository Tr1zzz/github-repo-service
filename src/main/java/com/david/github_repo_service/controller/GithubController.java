package com.david.github_repo_service.controller;

import com.david.github_repo_service.dto.RepoDto;
import com.david.github_repo_service.service.GithubService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class GithubController {

    private final GithubService service;

    @GetMapping("/{username}/repos")
    public ResponseEntity<List<RepoDto>> getRepos(@PathVariable String username) {
        return ResponseEntity.ok(service.fetchUserReposWithBranches(username));
    }
}
