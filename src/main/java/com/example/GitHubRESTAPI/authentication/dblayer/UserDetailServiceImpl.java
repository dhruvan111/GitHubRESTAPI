package com.example.GitHubRESTAPI.authentication.dblayer;

import com.example.GitHubRESTAPI.authentication.entity.User;
import com.example.GitHubRESTAPI.authentication.entity.UserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        return modelMapper.map(user, UserDetails.class);
    }
}
