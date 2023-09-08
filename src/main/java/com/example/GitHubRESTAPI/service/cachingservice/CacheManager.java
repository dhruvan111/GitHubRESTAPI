package com.example.GitHubRESTAPI.service.cachingservice;

import com.example.GitHubRESTAPI.DTO.GitBranchInfoDTO;
import com.example.GitHubRESTAPI.DTO.GitRepoCommitDTO;
import com.example.GitHubRESTAPI.DTO.GitRepoInfoDTO;
import com.example.GitHubRESTAPI.DTO.GitUserDTO;

import java.io.IOException;
import java.util.List;

public interface CacheManager {
    List<GitRepoInfoDTO> getCachedRepoInfo(String username) throws IOException;
    GitUserDTO getCachedUser(String username) throws IOException;
    List<GitRepoCommitDTO> getCachedRepoCommits(String ownerName, String repoName) throws IOException;
    GitBranchInfoDTO getCachedBranchInfo(String ownerName, String repoName) throws IOException;
}
