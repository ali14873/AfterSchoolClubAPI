package co.uk.afterschoolclub.userregistration.ClubMember;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "club_member")
public class ClubMemberTable {

    @Id
    @Column(name = "ClubMemberID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @JoinColumn(name = "ClubID", nullable = false)
    private UUID clubId;

    @Size(max = 255)
    @Column(name = "MembershipStatus")
    private String MembershipStatus;

    @Size(max = 255)
    @Column(name = "UserId")
    private UUID userId;

}
