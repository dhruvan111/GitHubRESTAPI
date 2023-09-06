package com.example.GitHubRESTAPI.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "repos")
public class GitRepoInfoDTO {
    @Id
    private long id;
    private String owner;
    private String name;
    private String description;
    private String language;

    public GitRepoInfoDTO() {
        // for ser & de-ser
    }

    public GitRepoInfoDTO(long id, String owner, String name, String description, String language) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
