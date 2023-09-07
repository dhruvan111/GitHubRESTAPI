package com.example.GitHubRESTAPI.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "branches")
public class GitBranchInfo {
    private int branchCount;
    private List<Branch> branchList;

    public GitBranchInfo() {
        // for ser & de-ser
    }

    public GitBranchInfo(int branchCount, List<Branch> branchList) {
        this.branchCount = branchCount;
        this.branchList = branchList;
    }

    public int getBranchCount() {
        return branchCount;
    }

    public void setBranchCount(int branchCount) {
        this.branchCount = branchCount;
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }
}
