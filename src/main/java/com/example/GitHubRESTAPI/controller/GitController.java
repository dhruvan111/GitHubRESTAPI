package com.example.GitHubRESTAPI.controller;

import com.example.GitHubRESTAPI.model.GitHubUserDTO;
import com.example.GitHubRESTAPI.model.GitRepoDTO;
import com.example.GitHubRESTAPI.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    public GitController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/users/{username}/repo")
    public ResponseEntity<List<GitRepoDTO>> getRepositories(@PathVariable String username) throws IOException {
        List<GitRepoDTO> repositories = gitHubService.getRepositories(username);
        return ResponseEntity.ok(repositories);
    }

    @GetMapping("/users/{username}/info")
    public ResponseEntity<GitHubUserDTO> getUserInfo(@PathVariable String username) throws IOException {
        GitHubUserDTO gitHubUserDTO = gitHubService.getUserInfo(username);
        return ResponseEntity.ok(gitHubUserDTO);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<String> getName(String username) throws IOException {
        String name = "Dhruvan";
        gitHubService.getUserInfo(username);
        return ResponseEntity.ok(name);
    }
}
