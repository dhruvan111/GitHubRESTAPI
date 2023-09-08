package com.example.GitHubRESTAPI.controller;

import com.example.GitHubRESTAPI.DTO.GitBranchInfoDTO;
import com.example.GitHubRESTAPI.DTO.GitRepoCommitDTO;
import com.example.GitHubRESTAPI.DTO.GitRepoInfoDTO;
import com.example.GitHubRESTAPI.DTO.GitUserDTO;
import com.example.GitHubRESTAPI.service.cachingservice.CacheManager;
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
    private final CacheManager cacheManager;

    @Autowired
    public GitController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @GetMapping("/users/{username}/repo")
    public ResponseEntity<List<GitRepoInfoDTO>> getRepositories(@PathVariable String username) throws IOException {

        List<GitRepoInfoDTO> repoInfoDTOList = cacheManager.getCachedRepoInfo(username);
        return ResponseEntity.ok(repoInfoDTOList);
    }

    @GetMapping("/users/{username}/info")
    public ResponseEntity<GitUserDTO> getUserInfo(@PathVariable String username) throws IOException {

        GitUserDTO gitUserDTO = cacheManager.getCachedUser(username);
        return ResponseEntity.ok(gitUserDTO);
    }

    @GetMapping("/users/{owner-name}/{repoName}/commits")
    public ResponseEntity<List<GitRepoCommitDTO>> getCommitInfo(@PathVariable("owner-name") String ownerName, @PathVariable("repoName") String repoName) throws IOException {

        List<GitRepoCommitDTO> gitRepoCommitDTOS = cacheManager.getCachedRepoCommits(ownerName, repoName);
        return ResponseEntity.ok(gitRepoCommitDTOS);
    }

    @GetMapping("/users/{owner-name}/{repoName}/branches")
    public ResponseEntity<GitBranchInfoDTO> getBranchInfo(@PathVariable("owner-name") String ownerName, @PathVariable("repoName") String repoName) throws IOException {

        GitBranchInfoDTO gitBranchInfoDTO = cacheManager.getCachedBranchInfo(ownerName, repoName);
        return ResponseEntity.ok(gitBranchInfoDTO);
    }
}
