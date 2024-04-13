package co.uk.afterschoolclub.userregistration.StudentEmergencyInfo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentEmergencyInfoRepository extends CrudRepository<StudentEmergencyInfoView, UUID> {

    // Count the total number of emergency contacts
    // Count the total number of emergency contacts associated with students
    @Query("SELECT COUNT(e) FROM StudentEmergencyInfoView e WHERE e.studentId IS NOT NULL")
    Long countTotalEmergencyContacts();

    // Count the number of students that have at least one emergency contact
    @Query("SELECT COUNT(DISTINCT e.studentId) FROM StudentEmergencyInfoView e WHERE e.studentId IS NOT NULL")
    Long countStudentsWithEmergencyContacts();

    // Count the number of students that have more than one emergency contact
    @Query(value = "SELECT COUNT(*) FROM (SELECT studentid FROM student_emergency_contact_view WHERE studentid IS NOT NULL GROUP BY studentid HAVING COUNT(*) > 1) AS subquery", nativeQuery = true)
    Long countStudentsWithMultipleEmergencyContacts();

}
