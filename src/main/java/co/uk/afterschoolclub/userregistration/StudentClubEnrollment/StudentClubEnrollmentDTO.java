package co.uk.afterschoolclub.userregistration.StudentClubEnrollment;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentClubEnrollmentDTO {
    private UUID studentId;
    private String firstName;
    private String lastName;
    private UUID clubId;
    private String clubName;
    private String membershipStatus;
}
