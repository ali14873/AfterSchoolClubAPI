package co.uk.afterschoolclub.userregistration.Auth;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LoginResponse {
    private String currentUserName;
    private String accessToken;
    private UUID userId;
    private String roleType;
}
