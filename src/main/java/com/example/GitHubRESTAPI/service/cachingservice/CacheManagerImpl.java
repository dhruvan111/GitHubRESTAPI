package com.example.GitHubRESTAPI.service.cachingservice;

import com.example.GitHubRESTAPI.DTO.GitBranchInfoDTO;
import com.example.GitHubRESTAPI.DTO.GitRepoCommitDTO;
import com.example.GitHubRESTAPI.DTO.GitRepoInfoDTO;
import com.example.GitHubRESTAPI.DTO.GitUserDTO;
import com.example.GitHubRESTAPI.service.gitAPIservice.GitHubService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class CacheManagerImpl implements CacheManager {

    private final GitHubService gitHubService;
    private final Cache<String, List<GitRepoInfoDTO>> gitRepoInfoCache;
    private final Cache<String, GitUserDTO> gitUserCache;
    private final Cache<String, List<GitRepoCommitDTO>> gitRepoCommitCache;
    private final Cache<String, GitBranchInfoDTO> gitBranchCache;

    @Autowired
    public CacheManagerImpl(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
        this.gitRepoInfoCache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.HOURS)
                .maximumSize(1000)
                .build();

        this.gitUserCache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.HOURS)
                .maximumSize(1000)
                .build();

        this.gitRepoCommitCache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.HOURS)
                .maximumSize(1000)
                .build();

        this.gitBranchCache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.HOURS)
                .maximumSize(1000)
                .build();
    }

    @Override
    public List<GitRepoInfoDTO> getCachedRepoInfo(String username) throws IOException {
        List<GitRepoInfoDTO> gitRepoInfoDTOS = gitRepoInfoCache.getIfPresent(username);
        if (gitRepoInfoDTOS == null){
            gitRepoInfoDTOS = gitHubService.getRepositories(username);
            gitRepoInfoCache.put(username, gitRepoInfoDTOS);
        }
        return gitRepoInfoDTOS;
    }

    @Override
    public GitUserDTO getCachedUser(String username) throws IOException {
        GitUserDTO gitUserDTO = gitUserCache.getIfPresent(username);
        if (gitUserDTO == null){
            gitUserDTO = gitHubService.getUserInfo(username);
            gitUserCache.put(username, gitUserDTO);
        }
        return gitUserDTO;
    }

    @Override
    public List<GitRepoCommitDTO> getCachedRepoCommits(String ownerName, String repoName) throws IOException {
        List<GitRepoCommitDTO> gitRepoCommitDTOS = gitRepoCommitCache.getIfPresent(repoName);
        if (gitRepoCommitDTOS == null){
            gitRepoCommitDTOS = gitHubService.getCommitInfo(ownerName, repoName);
            gitRepoCommitCache.put(repoName, gitRepoCommitDTOS);
        }
        return gitRepoCommitDTOS;
    }

    @Override
    public GitBranchInfoDTO getCachedBranchInfo(String ownerName, String repoName) throws IOException {
        GitBranchInfoDTO gitBranchInfoDTO = gitBranchCache.getIfPresent(repoName);
        if (gitBranchInfoDTO == null){
            gitBranchInfoDTO = gitHubService.getBranchInfo(ownerName, repoName);
            gitBranchCache.put(repoName, gitBranchInfoDTO);
        }
        return gitBranchInfoDTO;
    }
}
