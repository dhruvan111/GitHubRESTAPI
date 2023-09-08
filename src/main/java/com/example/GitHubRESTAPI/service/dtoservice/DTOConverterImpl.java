package com.example.GitHubRESTAPI.service.dtoservice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DTOConverterImpl implements DTOConverter {
    private final ModelMapper modelMapper;
    @Autowired
    public DTOConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public <T, U> U convertToDTO(T source, Class<U> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}
