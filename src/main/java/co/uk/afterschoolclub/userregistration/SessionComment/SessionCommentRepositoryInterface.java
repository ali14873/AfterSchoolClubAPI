package co.uk.afterschoolclub.userregistration.SessionComment;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SessionCommentRepositoryInterface extends CrudRepository<SessionCommentTable, UUID> {
}
