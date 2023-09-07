package com.example.GitHubRESTAPI.service.gitAPIservice;

import com.example.GitHubRESTAPI.DTO.GitBranchInfoDTO;
import com.example.GitHubRESTAPI.DTO.GitRepoCommitDTO;
import com.example.GitHubRESTAPI.DTO.GitRepoInfoDTO;
import com.example.GitHubRESTAPI.DTO.GitUserDTO;
import com.example.GitHubRESTAPI.config.components.GitHubAPIClient;
import com.example.GitHubRESTAPI.model.*;
import com.example.GitHubRESTAPI.model.GitUser;
import com.example.GitHubRESTAPI.service.dtoservice.DTOConverter;
import org.kohsuke.github.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GitHubServiceImpl implements GitHubService {
    private final GitHub gitHub;
    private final DTOConverter dtoConverter;

    @Autowired
    public GitHubServiceImpl(GitHubAPIClient gitHubAPIClient, ModelMapper modelMapper, DTOConverter dtoConverter) {
        this.gitHub = gitHubAPIClient.getGitHubClient();
        this.dtoConverter = dtoConverter;
    }

    public List<GitRepoInfo> getRepoModel(List<GHRepository> ghRepositoryList){

        // mapping GHRepo to our required content
        List<GitRepoInfo> gitRepoInfoList = new ArrayList<>();
        for (GHRepository repository:ghRepositoryList){
            GitRepoInfo gitRepoInfoDTO = dtoConverter.convertToDTO(repository, GitRepoInfo.class);
            gitRepoInfoList.add(gitRepoInfoDTO);
        }
        return gitRepoInfoList;
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
        List<GitRepoInfo> gitRepoInfoList = getRepoModel(repositories);

        List<GitRepoInfoDTO> gitRepoInfoDTOS = new ArrayList<>();
        for (GitRepoInfo gitRepoInfo : gitRepoInfoList){
            GitRepoInfoDTO gitRepoInfoDTO = dtoConverter.convertToDTO(gitRepoInfo, GitRepoInfoDTO.class);
            gitRepoInfoDTOS.add(gitRepoInfoDTO);
        }
        return gitRepoInfoDTOS;
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
    public GitUserDTO getUserInfo(String username) throws IOException {
        GHUser ghUser = gitHub.getUser(username);

        // creating gitUser with username and filling it with essential info in model class
        GitUser gitUser = new GitUser(ghUser.getId(), ghUser.getName(), ghUser.getEmail(), ghUser.getLocation(), ghUser.getBio(), ghUser.getCompany());

        List<String> followersList = getFollowersList(ghUser);
        gitUser.setFollowers(followersList);

        List<String> starredRepoList = getStarredRepoList(ghUser);
        gitUser.setStarredRepos(starredRepoList);

        return dtoConverter.convertToDTO(gitUser, GitUserDTO.class);
    }

    @Override
    public List<GitRepoCommitDTO> getCommitInfo(String ownerName, String repoName) throws IOException {
        GHRepository ghRepository = gitHub.getRepository(ownerName + "/" + repoName);

        // storing in model class
        List<GitRepoCommit> gitRepoCommits = new ArrayList<>();
        for (GHCommit commit:ghRepository.listCommits()){
            GitRepoCommit gitRepoCommit = new GitRepoCommit(commit.getSHA1(), commit.getAuthor().getName(), commit.getCommitShortInfo().getMessage(), commit.getCommitDate());
            gitRepoCommits.add(gitRepoCommit);
        }

        // converting to DTO
        List<GitRepoCommitDTO> gitRepoCommitDTOList = new ArrayList<>();
        for (GitRepoCommit gitRepoCommit:gitRepoCommits){
            gitRepoCommitDTOList.add(dtoConverter.convertToDTO(gitRepoCommit, GitRepoCommitDTO.class));
        }
        return gitRepoCommitDTOList;
    }

    @Override
    public GitBranchInfoDTO getBranchInfo(String ownerName,String repoName) throws IOException {
        GHRepository ghRepository = gitHub.getRepository(ownerName + "/" + repoName);
        Map<String, GHBranch> ghBranchMap = ghRepository.getBranches();

        int count = ghBranchMap.size();

        // storing all info in model class
        GitBranchInfo gitBranchInfo = new GitBranchInfo();
        gitBranchInfo.setBranchCount(count);

        List<Branch> branchList = new ArrayList<>();
        for (Map.Entry<String, GHBranch> entry:ghBranchMap.entrySet()){
            Branch branch = new Branch(entry.getValue().getName());
            branchList.add(branch);
        }
        gitBranchInfo.setBranchList(branchList);

        // converting to DTO
        return dtoConverter.convertToDTO(gitBranchInfo, GitBranchInfoDTO.class);
    }
}
