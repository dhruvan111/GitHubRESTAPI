package com.example.GitHubRESTAPI.authentication.entity.roles;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "role")
public class Role {
    @Id
    private int id;
    private RoleName name;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public RoleName getName() {
        return name;
    }
    public void setName(RoleName name) {
        this.name = name;
    }
}
