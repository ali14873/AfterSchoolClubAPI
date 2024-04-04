package co.uk.afterschoolclub.userregistration.CriticalIncident;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CriticalIncidentRepositoryInterface extends CrudRepository<CriticalIncidentTable, UUID> {
}
