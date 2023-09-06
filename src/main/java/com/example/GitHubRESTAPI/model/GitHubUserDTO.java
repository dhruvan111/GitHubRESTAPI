package com.example.GitHubRESTAPI.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class GitHubUserDTO {
    @Id
    private long id;
    private String name;
    private String email;
    private String location;
    private String bio;
    private List<String> followers;
    private List<String> starredRepos;
    private String company;

    public GitHubUserDTO() {
        // for ser & de-ser
    }

    public GitHubUserDTO(long id, String name, String email, String location, String bio, String company) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.location = location;
        this.bio = bio;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public List<String> getStarredRepos() {
        return starredRepos;
    }

    public void setStarredRepos(List<String> starredRepos) {
        this.starredRepos = starredRepos;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
