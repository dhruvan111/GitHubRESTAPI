package com.example.GitHubRESTAPI.config.components;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubAPIClient {
    // Singleton design pattern
    private final GitHub gitHub;
    @Autowired
    public GitHubAPIClient(@Value("${github.token}") String accessToken) throws IOException {
        this.gitHub = new GitHubBuilder().withOAuthToken(accessToken).build();
    }
    public GitHub getGitHubClient(){
        return gitHub;
    }
}

