package com.example.GitHubRESTAPI.components;

import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubAPIClient {
    private final GitHub gitHub;

    @Value("${github.token}")
    private String accessToken;
    public GitHubAPIClient() throws IOException {
        this.gitHub = GitHub.connectUsingOAuth(accessToken);
    }
    public GitHub getGitHubClient(){
        return gitHub;
    }
}

