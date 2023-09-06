package com.example.GitHubRESTAPI.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "commits")
public class GitRepoCommitDTO {
    @Id
    private long id;

}
