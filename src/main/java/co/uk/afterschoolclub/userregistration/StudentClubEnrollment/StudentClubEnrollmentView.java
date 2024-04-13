package co.uk.afterschoolclub.userregistration.StudentClubEnrollment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Immutable
@Entity
@Table(name = "student_club_enrollment_view")
public class StudentClubEnrollmentView {


    @Id
    @Column(name = "StudentID") // Adjust the name based on the actual column name in snake case.
    private UUID studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "clubid")
    private UUID clubId;

    @Column(name = "club_name")
    private String clubName;

    @Column(name = "MembershipStatus")
    private String membershipStatus;

}
