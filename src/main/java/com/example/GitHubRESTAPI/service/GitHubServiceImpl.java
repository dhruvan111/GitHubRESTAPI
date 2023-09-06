package com.example.GitHubRESTAPI.service;

import com.example.GitHubRESTAPI.config.components.GitHubAPIClient;
import com.example.GitHubRESTAPI.model.GitHubUserDTO;
import com.example.GitHubRESTAPI.model.GitRepoCommitDTO;
import com.example.GitHubRESTAPI.model.GitRepoInfoDTO;
import org.kohsuke.github.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubServiceImpl implements GitHubService{
    private final GitHub gitHub;
    private final ModelMapper modelMapper;

    @Autowired
    public GitHubServiceImpl(GitHubAPIClient gitHubAPIClient, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.gitHub = gitHubAPIClient.getGitHubClient();
    }

    @Override
    public List<GitRepoInfoDTO> mapToGitRepoDTO(List<GHRepository> ghRepositoryList){

        // mapping GHRepo to our required content
        List<GitRepoInfoDTO> gitRepoInfoDTOList = new ArrayList<>();
        for (GHRepository repository:ghRepositoryList){
            GitRepoInfoDTO gitRepoInfoDTO = modelMapper.map(repository, GitRepoInfoDTO.class);
            gitRepoInfoDTOList.add(gitRepoInfoDTO);
        }
        return gitRepoInfoDTOList;
    }

    private List<GHRepository> getRepoByUsername(String username) throws IOException {
        GHUser ghUser = gitHub.getUser(username);
        List<GHRepository> repositories = new ArrayList<>();
        for (GHRepository repository:ghUser.listRepositories()) {
            repositories.add(repository);
        }
        return repositories;
    }

    @Override
    public List<GitRepoInfoDTO> getRepositories(String username) throws IOException {
        List<GHRepository> repositories = getRepoByUsername(username);
        return mapToGitRepoDTO(repositories);
    }

    private List<String> getFollowersList(GHUser ghUser) throws IOException {
        // adding followers
        PagedIterable<GHUser> ghUserPagedIterable = ghUser.listFollowers();
        List<String> followersList = new ArrayList<>();
        for (GHUser ghUser1:ghUserPagedIterable){
            followersList.add(ghUser1.getName());
        }
        return followersList;
    }

    private List<String> getStarredRepoList(GHUser ghUser){
        // adding starred repos marked by ${username}
        PagedIterable<GHRepository> pagedIterable = ghUser.listStarredRepositories();
        List<String> starredRepoList = new ArrayList<>();
        for (GHRepository ghRepository:pagedIterable){
            starredRepoList.add(ghRepository.getName());
        }
        return starredRepoList;
    }

    @Override
    public GitHubUserDTO getUserInfo(String username) throws IOException {
        GHUser ghUser = gitHub.getUser(username);

        // creating gitUser with username and filling it with essential info
        GitHubUserDTO gitHubUserDTO = new GitHubUserDTO(ghUser.getId(), ghUser.getName(), ghUser.getEmail(), ghUser.getLocation(), ghUser.getBio(), ghUser.getCompany());

        List<String> followersList = getFollowersList(ghUser);
        gitHubUserDTO.setFollowers(followersList);

        List<String> starredRepoList = getStarredRepoList(ghUser);
        gitHubUserDTO.setStarredRepos(starredRepoList);

        return gitHubUserDTO;
    }

    @Override
    public List<GitRepoCommitDTO> getCommitInfo(String ownerName, String repoName) throws IOException {
        GHRepository ghRepository = gitHub.getRepository(ownerName + "/" + repoName);

        List<GitRepoCommitDTO> gitRepoCommitDTOList = new ArrayList<>();
        for (GHCommit commit:ghRepository.listCommits()){
            GitRepoCommitDTO gitRepoCommitDTO = new GitRepoCommitDTO(commit.getSHA1(), commit.getAuthor().getName(), commit.getCommitShortInfo().getMessage(), commit.getCommitDate());
            gitRepoCommitDTOList.add(gitRepoCommitDTO);
        }
        return gitRepoCommitDTOList;
    }
}
