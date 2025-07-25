package com.david.github_repo_service.service;

import com.david.github_repo_service.client.GithubClient;
import com.david.github_repo_service.dto.BranchDto;
import com.david.github_repo_service.dto.RawRepo;
import com.david.github_repo_service.dto.RepoDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final GithubClient client;

    public List<RepoDto> fetchUserReposWithBranches(String username) {
        RawRepo[] rawRepos = client.getUserRepos(username);
        return Arrays.stream(rawRepos)
                .filter(r -> !r.fork())
                .map(r -> {
                    BranchDto[] branches = client.getBranches(r.owner().login(), r.name());
                    return new RepoDto(
                            r.name(),
                            r.owner().login(),
                            List.of(branches)
                    );
                })
                .toList();
    }
}