package co.uk.afterschoolclub.userregistration.HealthRecord;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface HealthRecordRepoInterface extends CrudRepository<HealthRecordTable, UUID> {

    List<HealthRecordTable> findByStudentID(UUID studentId);

    // Method to count all health records
    @Override
    long count();

    // Method to count incomplete health records where either details or medication is empty
    @Query("SELECT COUNT(h) FROM HealthRecordTable h WHERE h.details = '' OR h.medication = ''")
    long countIncompleteHealthRecords();

    @Query("SELECT COUNT(DISTINCT h.studentID) FROM HealthRecordTable h")
    long countStudentsWithHealthRecords();
}
