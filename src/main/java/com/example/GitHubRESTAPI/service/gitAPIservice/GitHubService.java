package com.example.GitHubRESTAPI.service.gitAPIservice;

import com.example.GitHubRESTAPI.DTO.GitBranchInfoDTO;
import com.example.GitHubRESTAPI.DTO.GitUserDTO;
import com.example.GitHubRESTAPI.DTO.GitRepoCommitDTO;
import com.example.GitHubRESTAPI.DTO.GitRepoInfoDTO;

import java.io.IOException;
import java.util.List;

public interface GitHubService {
    List<GitRepoInfoDTO> getRepositories(String username) throws IOException;
    GitUserDTO getUserInfo(String username) throws IOException;
    List<GitRepoCommitDTO> getCommitInfo(String ownerName, String repoName) throws IOException;
    GitBranchInfoDTO getBranchInfo(String ownerName, String repoName) throws IOException;
}
