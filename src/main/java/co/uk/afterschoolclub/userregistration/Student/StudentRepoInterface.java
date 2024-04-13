package co.uk.afterschoolclub.userregistration.Student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepoInterface extends CrudRepository<StudentTable, UUID> {

    Long countByStatus(String status);

    @Query(value = "SELECT COUNT(*) FROM student_table WHERE " +
            "(MONTH(date_of_birth) = MONTH(GETDATE()) AND DAY(date_of_birth) >= DAY(GETDATE())) " +
            "OR " +
            "(MONTH(date_of_birth) = MONTH(DATEADD(day, 30, GETDATE())) AND DAY(date_of_birth) <= DAY(DATEADD(day, 30, GETDATE()))) " +
            "OR " +
            "(MONTH(GETDATE()) > MONTH(DATEADD(day, 30, GETDATE())) AND " +
            "((MONTH(date_of_birth) > MONTH(GETDATE())) OR (MONTH(date_of_birth) < MONTH(DATEADD(day, 30, GETDATE())))))",
            nativeQuery = true)
    Long countUpcomingBirthdays();




}
