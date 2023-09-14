package com.example.GitHubRESTAPI.authentication.dblayer;

import com.example.GitHubRESTAPI.authentication.entity.UserDetails;

public interface UserDetailService {
    UserDetails loadByUsername(String username);
}
