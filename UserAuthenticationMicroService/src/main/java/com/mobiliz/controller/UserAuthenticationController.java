package com.mobiliz.controller;

import com.mobiliz.dto.request.SaveUserRequestDto;
import com.mobiliz.dto.response.SaveResponseDto;
import com.mobiliz.dto.response.TokenResponseDto;
import com.mobiliz.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.mobiliz.constants.EndPoints.*;

@RequestMapping(USER)
@RequiredArgsConstructor
@RestController
public class UserAuthenticationController {
    private final UserAuthenticationService userAuthenticationService;

    @PostMapping(SAVE)
    public ResponseEntity<SaveResponseDto> save(@RequestBody  SaveUserRequestDto dto){
        return ResponseEntity.ok(userAuthenticationService.saveUser(dto));
    }
    @GetMapping(TOKEN)
    public ResponseEntity<TokenResponseDto> getToken(Long id){
        return ResponseEntity.ok(userAuthenticationService.getToken(id));
    }
}
