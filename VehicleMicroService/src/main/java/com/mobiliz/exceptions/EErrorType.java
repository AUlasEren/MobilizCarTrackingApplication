package com.mobiliz.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {
    INVALID_PARAMETER(4000,"Invalid parameter entered", BAD_REQUEST),
    METHOD_MIS_MATCH_ERROR(4001,"The value you entered does not match the desired value.", BAD_REQUEST),
    METHOD_NOT_VALID_ARGUMENT_ERROR(4002,"Missing parameter submission in URL", BAD_REQUEST),
    INVALID_TOKEN(4004,"Invalid token", UNAUTHORIZED),
    NOT_DECODED(4005,"Token can not decoded", INTERNAL_SERVER_ERROR),
    HTTP_MESSAGE_NOT_READABLE(4006,"Http message not readable",BAD_REQUEST),
    TOKEN_NOT_CREATED(4007,"Token can not be created", UNAUTHORIZED),
    UNEXPECTED_ERROR(4008,"Unexpected Error Occured", INTERNAL_SERVER_ERROR),
    VEHICLE_NOT_FOUND(4104,"Vehicle not found", BAD_REQUEST),
    USER_NOT_ACTIVE(1103,"User not active", UNAUTHORIZED),
    COMPANY_ID_MISMATCH(4009, "Company id mismatch with user's company", UNAUTHORIZED),
    REGION_NOT_FOUND(4010, "Region not found", BAD_REQUEST),
    REGION_MISMATCH(4011, "User don't have access to this region", UNAUTHORIZED);


    private int code;
    private String message;
    private HttpStatus httpStatus;
}
