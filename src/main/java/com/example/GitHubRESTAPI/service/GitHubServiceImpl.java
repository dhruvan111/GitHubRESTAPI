package com.example.GitHubRESTAPI.service;

import com.example.GitHubRESTAPI.config.components.GitHubAPIClient;
import com.example.GitHubRESTAPI.model.GitHubUserDTO;
import com.example.GitHubRESTAPI.model.GitRepoDTO;
import org.kohsuke.github.*;
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

        // mapping GHRepo to our required content
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

    @Override
    public GitHubUserDTO getUserInfo(String username) throws IOException {
        GitHub gitHub = gitHubAPIClient.getGitHubClient();
        GHUser ghUser = gitHub.getUser(username);

        // creating gitUser with username and filling it with essential info
        GitHubUserDTO gitHubUserDTO = new GitHubUserDTO(ghUser.getId(), ghUser.getName(), ghUser.getEmail(), ghUser.getLocation(), ghUser.getBio(), ghUser.getCompany());

        // adding followers
        PagedIterable<GHUser> ghUserPagedIterable = ghUser.listFollowers();
        List<String> followersList = new ArrayList<>();
        for (GHUser ghUser1:ghUserPagedIterable){
            followersList.add(ghUser1.getName());
        }
        gitHubUserDTO.setFollowers(followersList);

        // adding starred repos marked by ${username}
        PagedIterable<GHRepository> pagedIterable = ghUser.listStarredRepositories();
        List<String> starredRepoList = new ArrayList<>();
        for (GHRepository ghRepository:pagedIterable){
            starredRepoList.add(ghRepository.getName());
        }
        gitHubUserDTO.setStarredRepos(starredRepoList);

        return gitHubUserDTO;
    }
}
