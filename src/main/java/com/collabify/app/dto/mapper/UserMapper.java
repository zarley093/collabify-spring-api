package com.collabify.app.dto.mapper;

import com.collabify.app.dto.UserDto;
import com.collabify.app.model.User;

public final class UserMapper {
  private UserMapper() {}
  public static UserDto toDto(User u) {
    if (u == null) return null;
    return new UserDto();
  }
}
