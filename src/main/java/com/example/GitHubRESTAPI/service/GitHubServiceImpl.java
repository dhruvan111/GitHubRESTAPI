package com.example.GitHubRESTAPI.service;

import com.example.GitHubRESTAPI.components.GitHubAPIClient;
import com.example.GitHubRESTAPI.model.GitRepoDTO;
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
public class GitHubServiceImpl implements GitHubService{
    private final GitHubAPIClient gitHubAPIClient;
    private final ModelMapper modelMapper;

    @Autowired
    public GitHubServiceImpl(GitHubAPIClient gitHubAPIClient, ModelMapper modelMapper) {
        this.gitHubAPIClient = gitHubAPIClient;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GitRepoDTO> mapToGitRepoDTO(List<GHRepository> ghRepositoryList){

        List<GitRepoDTO> gitRepoDTOList = new ArrayList<>();
        for (GHRepository repository:ghRepositoryList){
            GitRepoDTO gitRepoDTO = modelMapper.map(repository, GitRepoDTO.class);
            gitRepoDTOList.add(gitRepoDTO);
        }
        return gitRepoDTOList;
    }

    @Override
    public List<GitRepoDTO> getRepositories(String username) throws IOException {
        GitHub gitHub = gitHubAPIClient.getGitHubClient();
        GHUser ghUser = gitHub.getUser(username);

        List<GHRepository> repositories = new ArrayList<>();
        for (GHRepository repository:ghUser.listRepositories()) {
            repositories.add(repository);
        }
        return mapToGitRepoDTO(repositories);
    }
}
