package com.example.GitHubRESTAPI.controller;

import com.example.GitHubRESTAPI.service.GitHubService;
import org.kohsuke.github.GHRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GitController {
    private final GitHubService gitHubService;

    public GitController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/repos/{username}")
    public ResponseEntity<List<GHRepository>> getRepositories(@PathVariable String username) throws IOException {
        List<GHRepository> repositories = gitHubService.getRepositories(username);
        return ResponseEntity.ok(repositories);
    }
}
