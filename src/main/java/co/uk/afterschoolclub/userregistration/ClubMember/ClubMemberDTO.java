package co.uk.afterschoolclub.userregistration.ClubMember;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClubMemberDTO {

    private UUID id;
    private UUID ClubID;
    private String MembershipStatus;
    private UUID UserID;
}
