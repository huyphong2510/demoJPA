package com.vti.demo.service;

import javax.servlet.http.HttpServletResponse;

import com.vti.demo.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

public class JWTTokenService {

    private static final long EXPIRATION_TIME = 864000000; // 10 days
    private static final String SECRET = "123456";
    private static final String PREFIX_TOKEN = "Bearer";
    private static final String AUTHORIZATION = "Authorization";

    public static void addJWTTokenToHeader(HttpServletResponse response, String username) throws IOException {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        response.addHeader(AUTHORIZATION, PREFIX_TOKEN + " " + JWT);

        UUID uuid = UUID.randomUUID();
        String refreshToken = uuid.toString();
        response.getWriter().write(refreshToken);
        response.flushBuffer();

    }

    public static Authentication parseTokenToUserInformation(HttpServletRequest request, IUserService service) {
        String token = request.getHeader(AUTHORIZATION);

        if (token == null) {
            return null;
        }

        // Parse token
        String username;
        try {
            username = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(PREFIX_TOKEN, ""))
                    .getBody()
                    .getSubject();
        } catch (MalformedJwtException e) {
            return null;
        }
        User user = service.findByUserName(username);

        if(user == null) {
            return null;
        }

        return username != null ?
                new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        AuthorityUtils.createAuthorityList(user.getRole())) :
                null;
    }
}