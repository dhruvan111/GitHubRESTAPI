package com.example.GitHubRESTAPI.service.cachingservice;

import com.example.GitHubRESTAPI.DTO.GitBranchInfoDTO;
import com.example.GitHubRESTAPI.DTO.GitRepoCommitDTO;
import com.example.GitHubRESTAPI.DTO.GitRepoInfoDTO;
import com.example.GitHubRESTAPI.DTO.GitUserDTO;

import java.util.List;

public interface CacheManager {
    List<GitRepoInfoDTO> getCachedRepoInfo(String username);
    GitUserDTO getCachedUser(String username);
    List<GitRepoCommitDTO> getCachedRepoCommits(String ownerName, String repoName);
    GitBranchInfoDTO getBranchInfo(String ownerName, String repoName);
}
