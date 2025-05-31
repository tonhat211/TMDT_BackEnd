package com.example.passfashion.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.passfashion.dto.Response.UserResponse;
import com.example.passfashion.model.User;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    @Mapping(source = "image.url", target = "imageUrl")
    UserResponse toUserResponse(User user);
}