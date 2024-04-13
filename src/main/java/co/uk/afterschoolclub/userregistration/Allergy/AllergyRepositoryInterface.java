package co.uk.afterschoolclub.userregistration.Allergy;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AllergyRepositoryInterface extends CrudRepository<AllergyTable, UUID> {

    List<AllergyTable> findByStudentId(UUID studentId);

    // Counts unique students with any allergy
    @Query(value = "SELECT COUNT(DISTINCT studentid) FROM allergies", nativeQuery = true)
    Long countUniqueStudentsWithAllergies();

    // Counts unique students with allergies of high severity
    @Query(value = "SELECT COUNT(DISTINCT studentid) FROM allergies WHERE severity = 'High'", nativeQuery = true)
    Long countStudentsWithHighSeverityAllergies();

    // Counts unique students with allergies of medium severity
    @Query(value = "SELECT COUNT(DISTINCT studentid) FROM allergies WHERE severity = 'Medium'", nativeQuery = true)
    Long countStudentsWithMediumSeverityAllergies();

}
