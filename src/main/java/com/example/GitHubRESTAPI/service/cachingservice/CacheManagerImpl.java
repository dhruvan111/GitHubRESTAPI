package com.example.GitHubRESTAPI.service.cachingservice;

import com.example.GitHubRESTAPI.DTO.GitBranchInfoDTO;
import com.example.GitHubRESTAPI.DTO.GitRepoCommitDTO;
import com.example.GitHubRESTAPI.DTO.GitRepoInfoDTO;
import com.example.GitHubRESTAPI.DTO.GitUserDTO;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class CacheManagerImpl implements CacheManager {

    private final Cache<String, List<GitRepoInfoDTO>> gitRepoInfoCache;
    private final Cache<String, GitUserDTO> gitUserCache;
    private final Cache<String, List<GitRepoCommitDTO>> gitRepoCommitCache;
    private final Cache<String, GitBranchInfoDTO> gitBranchCache;

    public CacheManagerImpl() {
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
    public List<GitRepoInfoDTO> getCachedRepoInfo(String username) {
        return gitRepoInfoCache.getIfPresent(username);
    }

    @Override
    public GitUserDTO getCachedUser(String username) {
        return gitUserCache.getIfPresent(username);
    }

    @Override
    public List<GitRepoCommitDTO> getCachedRepoCommits(String ownerName, String repoName) {
        return gitRepoCommitCache.getIfPresent(repoName);
    }

    @Override
    public GitBranchInfoDTO getBranchInfo(String ownerName, String repoName) {
        return null;
    }
}
