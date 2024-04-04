package co.uk.afterschoolclub.userregistration.SessionResourceUsage;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SessionResourceUsageRepositoryInterface extends CrudRepository<SessionResourceUsageTable, UUID> {
}
