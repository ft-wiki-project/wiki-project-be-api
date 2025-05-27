package com.cooksys.wikiProjectAPI.mappers;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.cooksys.wikiProjectAPI.dtos.UserRequestDto;
import com.cooksys.wikiProjectAPI.dtos.UserResponseDto;
import com.cooksys.wikiProjectAPI.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
  @Mapping(target = "username", source = "credentials.username")
  List<UserResponseDto> entitiesToDtos(List<User> users);
  
  User requestDtoToEntity(UserRequestDto userRequestDto);

  @Mapping(target = "username", source = "credentials.username")
  UserResponseDto entityToDto(User user);
}
