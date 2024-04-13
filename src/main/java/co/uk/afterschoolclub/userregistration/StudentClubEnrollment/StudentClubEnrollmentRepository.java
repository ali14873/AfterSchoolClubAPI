package co.uk.afterschoolclub.userregistration.StudentClubEnrollment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentClubEnrollmentRepository extends CrudRepository<StudentClubEnrollmentView, UUID> {

    // Count of students that are not a member of any club
    // Count of students that are not a member of any club
    @Query("SELECT COUNT(sc) FROM StudentClubEnrollmentView sc WHERE sc.membershipStatus IS NULL")
    Long countStudentsWithoutClubs();

    // Count of students that are enrolled in at least one club
    @Query("SELECT COUNT(DISTINCT sc.studentId) FROM StudentClubEnrollmentView sc WHERE sc.membershipStatus IS NOT NULL")
    Long countStudentsEnrolledInClubs();

    // Count of how many clubs there are
    @Query("SELECT COUNT(DISTINCT sc.clubId) FROM StudentClubEnrollmentView sc")
    Long countClubs();
}
