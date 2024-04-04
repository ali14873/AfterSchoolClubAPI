package co.uk.afterschoolclub.userregistration.Session;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SessionRepositoryInterface extends CrudRepository<SessionTable, UUID> {
}
