package com.example.GitHubRESTAPI.service;

import com.example.GitHubRESTAPI.model.GitRepoDTO;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.List;

public interface GitHubService {
    List<GitRepoDTO> mapToGitRepoDTO(List<GHRepository> ghRepositoryList);
    List<GitRepoDTO> getRepositories(String username) throws IOException;
}
