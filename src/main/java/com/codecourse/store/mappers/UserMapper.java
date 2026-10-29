package com.codecourse.store.mappers;

import com.codecourse.store.dtos.RegisterUserRequest;
import com.codecourse.store.dtos.UpdateUserRequest;
import com.codecourse.store.dtos.UserDto;
import com.codecourse.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(RegisterUserRequest request);

    void update(UpdateUserRequest request, @MappingTarget User user);
}
