package com.example.GitHubRESTAPI.service;

import com.example.GitHubRESTAPI.components.GitHubAPIClient;
import com.example.GitHubRESTAPI.model.GitRepo;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubService {
    private final GitHubAPIClient gitHubAPIClient;
    private final ModelMapper modelMapper;

    @Autowired
    public GitHubService(GitHubAPIClient gitHubAPIClient, ModelMapper modelMapper) {
        this.gitHubAPIClient = gitHubAPIClient;
        this.modelMapper = modelMapper;
    }

    public List<GitRepo> parseData(List<GHRepository> ghRepositoryList){

        List<GitRepo> gitRepoList = new ArrayList<>();
        for (GHRepository repository:ghRepositoryList){
            GitRepo gitRepo = modelMapper.map(repository, GitRepo.class);
            gitRepoList.add(gitRepo);
        }
        return gitRepoList;
    }

    public List<GitRepo> getRepositories(String username) throws IOException {
        GitHub gitHub = gitHubAPIClient.getGitHubClient();
        GHUser ghUser = gitHub.getUser(username);

        List<GHRepository> repositories = new ArrayList<>();
        for (GHRepository repository:ghUser.listRepositories()) {
            repositories.add(repository);
        }
        return parseData(repositories);
    }
}
