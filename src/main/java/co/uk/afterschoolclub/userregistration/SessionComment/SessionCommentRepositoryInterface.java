package co.uk.afterschoolclub.userregistration.SessionComment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface SessionCommentRepositoryInterface extends CrudRepository<SessionCommentTable, UUID> {

    List<SessionCommentTable> findBySessionID(UUID sessionID);
    List<SessionCommentTable> findByUserID(UUID userID);

    @Query("SELECT AVG(c.rating) FROM SessionCommentTable c WHERE c.sessionID = ?1")
    Double findAverageRatingBySessionID(UUID sessionID);

    @Query("SELECT COUNT(c) FROM SessionCommentTable c WHERE c.sessionID = ?1")
    Long countBySessionID(UUID sessionID);
}
