package com.example.GitHubRESTAPI.controller;

import com.example.GitHubRESTAPI.model.GitRepoDTO;
import com.example.GitHubRESTAPI.service.GitHubServiceImpl;
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
    private final GitHubServiceImpl gitHubServiceImpl;
    @Autowired
    public GitController(GitHubServiceImpl gitHubServiceImpl) {
        this.gitHubServiceImpl = gitHubServiceImpl;
    }

    @GetMapping("/repo/{username}")
    public ResponseEntity<List<GitRepoDTO>> getRepositories(@PathVariable String username) throws IOException {
        List<GitRepoDTO> repositories = gitHubServiceImpl.getRepositories(username);
        return ResponseEntity.ok(repositories);
    }

    @GetMapping("/name")
    public ResponseEntity<String> getName(){
        String name = "Dhruvan";
        return ResponseEntity.ok(name);
    }
}
