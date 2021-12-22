package com.example.backend.mapper;

import com.example.backend.dao.EmployeeDto;
import com.example.backend.entity.EmployeeEntity;

public class EmployeeMapper {

    public static EmployeeDto toDto(EmployeeEntity entity) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId1(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setUsername(entity.getUsername());
        dto.setCategory(entity.getCategory());
        dto.setCity(entity.getCity());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setPrice(entity.getPrice());
        dto.setPictureUrl(entity.getPictureUrl());
        dto.setAboutMyself(entity.getAboutMyself());
        return dto;
    }
    public static EmployeeEntity toEntity(EmployeeDto dto) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(dto.getId1());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setUsername(dto.getUsername());
        entity.setCategory(dto.getCategory());
        entity.setCity(dto.getCity());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setPrice(dto.getPrice());
        entity.setPictureUrl(dto.getPictureUrl());
        entity.setAboutMyself(dto.getAboutMyself());
        return entity;
    }
}
