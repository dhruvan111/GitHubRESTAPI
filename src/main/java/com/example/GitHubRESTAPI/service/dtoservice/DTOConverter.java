package com.example.GitHubRESTAPI.service.dtoservice;

public interface DTOConverter {
    <T, U> U convertToDTO(T source, Class<U> targetClass);
}
