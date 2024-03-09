package co.uk.afterschoolclub.userregistration.HealthRecord;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface HealthRecordRepoInterface extends CrudRepository<HealthRecordTable, UUID> {
}
