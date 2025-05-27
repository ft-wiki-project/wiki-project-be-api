package com.cooksys.wikiProjectAPI.mappers;
import org.mapstruct.Mapper;
import com.cooksys.wikiProjectAPI.dtos.ProfileDto;
import com.cooksys.wikiProjectAPI.embeddables.Profile;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
  Profile profileDtoToEntity(ProfileDto profileDto);
  
  ProfileDto profileEntityToDto(Profile profile);
}
