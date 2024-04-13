package co.uk.afterschoolclub.userregistration.CriticalIncident;

import co.uk.afterschoolclub.userregistration.EmergencyContact.EmergencyContactTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CriticalIncidentRepositoryInterface extends CrudRepository<CriticalIncidentTable, UUID> {

    List<CriticalIncidentTable> findBystudentID(UUID ID);

    @Query("SELECT COUNT(c) FROM CriticalIncidentTable c")
    Long countAllCriticalIncidents();

    @Query("SELECT COUNT(c) FROM CriticalIncidentTable c WHERE c.resolved = false")
    Long countUnresolvedCriticalIncidents();

    @Query(value = "SELECT COUNT(*) FROM critical_incidents c WHERE CONVERT(date, c.incident_date) > DATEADD(day, -30, GETDATE())", nativeQuery = true)
    Long countCriticalIncidentsLast30Days();


}
