package com.example.GitHubRESTAPI.controller;

import com.example.GitHubRESTAPI.model.GitRepo;
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

    @GetMapping("/repo/{username}")
    public ResponseEntity<List<GitRepo>> getRepositories(@PathVariable String username) throws IOException {
        List<GitRepo> repositories = gitHubService.getRepositories(username);
        return ResponseEntity.ok(repositories);
    }

    @GetMapping("/name")
    public ResponseEntity<String> getName(){
        String name = "Dhruvan";
        return ResponseEntity.ok(name);
    }
}
