package com.example.GitHubRESTAPI.service;

import com.example.GitHubRESTAPI.components.GitHubAPIClient;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GitHubService {
    private final GitHubAPIClient gitHubAPIClient;

    @Autowired
    public GitHubService(GitHubAPIClient gitHubAPIClient) {
        this.gitHubAPIClient = gitHubAPIClient;
    }

    public List<GHRepository> getRepositories(String username) throws IOException {
        GitHub gitHub = gitHubAPIClient.getGitHubClient();
        return gitHub.getUser(username).listRepositories().toList();
    }

}
