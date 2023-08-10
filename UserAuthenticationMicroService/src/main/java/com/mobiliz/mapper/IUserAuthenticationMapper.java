package com.mobiliz.mapper;

import com.mobiliz.dto.request.SaveUserRequestDto;
import com.mobiliz.dto.response.SaveResponseDto;
import com.mobiliz.repository.entity.UserAuthentication;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IUserAuthenticationMapper {
    IUserAuthenticationMapper INSTANCE = Mappers.getMapper(IUserAuthenticationMapper.class);
    UserAuthentication toUser(final SaveUserRequestDto dto);
    SaveResponseDto toUserResponseDto(final UserAuthentication userAuthentication);

}
