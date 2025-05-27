package com.cooksys.wikiProjectAPI.mappers;
import org.mapstruct.Mapper;
import com.cooksys.wikiProjectAPI.dtos.CredentialsDto;
import com.cooksys.wikiProjectAPI.embeddables.Credentials;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {
  Credentials credentialsDtoToEntity(CredentialsDto credentialsDto);
  
  CredentialsDto credentialsEntityToDto(Credentials credentials);
}
