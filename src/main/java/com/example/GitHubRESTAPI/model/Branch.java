package com.example.GitHubRESTAPI.model;

public class Branch {
    private String branchName;

    public Branch() {
    }

    public Branch(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
