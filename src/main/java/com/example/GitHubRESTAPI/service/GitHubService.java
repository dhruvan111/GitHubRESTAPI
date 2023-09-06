package com.example.GitHubRESTAPI.service;

import com.example.GitHubRESTAPI.model.GitHubUserDTO;
import com.example.GitHubRESTAPI.model.GitRepoCommitDTO;
import com.example.GitHubRESTAPI.model.GitRepoInfoDTO;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.List;

public interface GitHubService {
    List<GitRepoInfoDTO> mapToGitRepoDTO(List<GHRepository> ghRepositoryList);
    List<GitRepoInfoDTO> getRepositories(String username) throws IOException;
    GitHubUserDTO getUserInfo(String username) throws IOException;
    List<GitRepoCommitDTO> getCommitInfo(String ownerName, String repoName) throws IOException;
}
