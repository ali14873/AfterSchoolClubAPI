package co.uk.afterschoolclub.userregistration.SessionCompletion;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SessionCompletionRepositoryInterface extends CrudRepository<SessionCompletionTable, UUID> {
}
