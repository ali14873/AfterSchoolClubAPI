package co.uk.afterschoolclub.userregistration.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class AuthApplicationService {


    @Autowired
    private JwtEncoder jwtEncoder;

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 3600L;
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        System.out.println("token: "+ token);
        return token;
}

    public LoginResponse login(Authentication authentication) {
        LoginResponse response = LoginResponse
                .builder().
                build();
        IUserDetails userDetails = (IUserDetails) authentication.getDetails();
        response.setCurrentUserName(authentication.getName());
        response.setUserId(userDetails.getId());
        response.setAccessToken(generateToken(authentication));
        response.setRoleType(userDetails.getRole().getType());
        return response;
    }
}
