package com.mobiliz.service;

import com.mobiliz.dto.request.SaveUserRequestDto;
import com.mobiliz.dto.response.TokenResponseDto;
import com.mobiliz.dto.response.SaveResponseDto;
import com.mobiliz.exception.EErrorType;
import com.mobiliz.exception.UserManagerException;
import com.mobiliz.mapper.IUserAuthenticationMapper;
import com.mobiliz.repository.IUserAuthenticationRepository;
import com.mobiliz.repository.entity.UserAuthentication;
import com.mobiliz.repository.enums.ERole;
import com.mobiliz.utility.JwtTokenManager;
import com.mobiliz.utility.ServiceManager;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticationService extends ServiceManager<UserAuthentication,Long> {
    private final IUserAuthenticationRepository iUserAuthenticationRepository;
    private final JwtTokenManager jwtTokenManager;

    public UserAuthenticationService(IUserAuthenticationRepository iUserAuthenticationRepository, JwtTokenManager jwtTokenManager) {
        super(iUserAuthenticationRepository);
        this.iUserAuthenticationRepository = iUserAuthenticationRepository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public SaveResponseDto saveUser(SaveUserRequestDto dto) {
        Optional<UserAuthentication> userAuthentication = iUserAuthenticationRepository.findByEmail(dto.getEmail());
        if(userAuthentication.isPresent())
            throw new UserManagerException(EErrorType.USER_ALREADY_EXIST);
         userAuthentication = Optional.of(IUserAuthenticationMapper.INSTANCE.toUser(dto));
        save(userAuthentication.get());
        return IUserAuthenticationMapper.INSTANCE.toUserResponseDto(userAuthentication.get());

    }

    public TokenResponseDto getToken(Long id) {
        Optional<UserAuthentication> userAuthentication = iUserAuthenticationRepository.findById(id);
        if(userAuthentication.isEmpty())
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        Optional<String> token = jwtTokenManager.createToken(userAuthentication.get().getUserId(),
                userAuthentication.get().getRole(), userAuthentication.get().getStatus(),
                userAuthentication.get().getCompanyId(),userAuthentication.get().getRegions());
        return TokenResponseDto.builder().token(token.get()).build();
    }

}
