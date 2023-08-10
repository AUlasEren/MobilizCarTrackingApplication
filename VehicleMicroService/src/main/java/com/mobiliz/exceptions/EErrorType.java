package com.mobiliz.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {
    INVALID_PARAMETER(4000,"Invalid parameter entered", BAD_REQUEST),
    METHOD_MIS_MATCH_ERROR(4001,"The value you entered does not match the desired value.", BAD_REQUEST),
    METHOD_NOT_VALID_ARGUMENT_ERROR(4002,"Missing parameter submission in URL",BAD_REQUEST),
    INVALID_TOKEN(4004,"Invalid token",HttpStatus.BAD_REQUEST),
    NOT_DECODED(4005,"Token can not decoded", INTERNAL_SERVER_ERROR),
    HTTP_MESSAGE_NOT_READABLE(4006,"Http message not readable",BAD_REQUEST),
    TOKEN_NOT_CREATED(4007,"Token can not be created", INTERNAL_SERVER_ERROR),
    UNEXPECTED_ERROR(4008,"Unexpected Error Occured", INTERNAL_SERVER_ERROR),
    VEHICLE_NOT_FOUND(4104,"Vehicle not found" ,BAD_REQUEST ),
    USER_NOT_ACTIVE(1103,"User not active", BAD_REQUEST),
    ;


    private int code;
    private String message;
    private HttpStatus httpStatus;
}
