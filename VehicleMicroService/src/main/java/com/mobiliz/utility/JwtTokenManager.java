package com.mobiliz.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mobiliz.exceptions.EErrorType;
import com.mobiliz.exceptions.VehicleManagerException;
import com.mobiliz.repository.enums.EStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JwtTokenManager {
    @Value("${jwt.secretkey}")
    String secretKey;
    @Value("${jwt.issuer}")
    String issuer;
    @Value("${jwt.audience}")
    String audience;


    public Optional<String>createToken(Long id,EStatus status,Long companyId) {
        String token=null;
        Long exDate = 1000L*60*150;
        Date date=new Date(System.currentTimeMillis()+(1000*60*5));
        try {
            token= JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .withClaim("id",id)
                    .withClaim("companyId",companyId)
                    .withClaim("status", String.valueOf(status))
                    .withExpiresAt(new Date(System.currentTimeMillis()+exDate))
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);
        }catch (Exception e){
            return Optional.empty();
        }
    }


    public Boolean validateToken(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC512(secretKey);
            JWTVerifier verifier=JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT=verifier.verify(token);
            if (decodedJWT==null){
                return false;
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
        }
        return true;
    }

    public Optional<Long>getIdFromToken(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC512(secretKey);
            JWTVerifier verifier=JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT=verifier.verify(token);
            if (decodedJWT==null){
                throw new VehicleManagerException(EErrorType.NOT_DECODED);
            }
            Long id=decodedJWT.getClaim("userId").asLong();
            return Optional.of(id);
        }catch (Exception exception){
            throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
        }

    }
    public Optional<String> getRoleFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(this.secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(this.issuer).withAudience(new String[]{this.audience}).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) {
                throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
            } else {
                String role = decodedJWT.getClaim("role").asString();
                return Optional.of(role);
            }
        } catch (Exception var6) {
            System.out.println(var6.getMessage());
            throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
        }
    }
    public EStatus getStatusFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) {
                throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
            }
            EStatus status = decodedJWT.getClaim("status").as(EStatus.class);
            return status;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new VehicleManagerException(EErrorType.INVALID_TOKEN);

        }
    }
    public Optional<Long>getCompanyIdFromToken(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC512(secretKey);
            JWTVerifier verifier=JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT=verifier.verify(token);
            if (decodedJWT==null){
                throw new VehicleManagerException(EErrorType.NOT_DECODED);
            }
            Long id=decodedJWT.getClaim("companyId").asLong();
            return Optional.of(id);
        }catch (Exception exception){
            throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
        }

    }
    public List<Long>getRegionIdFromToken(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC512(secretKey);
            JWTVerifier verifier=JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT=verifier.verify(token);
            if (decodedJWT==null){
                throw new VehicleManagerException(EErrorType.NOT_DECODED);
            }
            List<Long> id=decodedJWT.getClaim("regionId").asList(Long.class);
            return id;
        }catch (Exception exception){
            throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
        }

    }


}
