package com.example.reddit.mapper;

import com.example.reddit.dto.UserDto;
import com.example.reddit.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto entityToDTO(UserEntity companyEntity);
    UserEntity dtoToEntity(UserDto companyDTO);

    @Mapping(target = "id", ignore = true)
    void updateFromDTO(UserDto dto, @MappingTarget UserEntity entity);
}
