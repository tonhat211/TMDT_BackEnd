package com.example.passfashion.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.passfashion.dto.Response.UserResponse;
import com.example.passfashion.model.User;

@Mapper(componentModel = "spring")
public interface IUserMapper {

  @Mapping(target = "imageUrl", expression = "java(user.getImage() != null ? user.getImage().getUrl() : null)")
  UserResponse toUserResponse(User user);
}