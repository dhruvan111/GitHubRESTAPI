package com.example.GitHubRESTAPI.components;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubAPIClient {

    private final GitHub gitHub;

    @Value("${github.token}")
    private String accessToken;

    @Autowired
    public GitHubAPIClient() throws IOException {
        this.gitHub = new GitHubBuilder().withOAuthToken("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").build();
    }

    public GitHub getGitHubClient(){
        return gitHub;
    }
}

