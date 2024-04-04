package co.uk.afterschoolclub.userregistration.SessionStaff;

import lombok.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SessionStaffDTO {
    private UUID sessionStaffID;
    private UUID sessionID;
    private UUID staffID;
    private String role;
}
