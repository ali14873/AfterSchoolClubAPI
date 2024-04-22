package co.uk.afterschoolclub.userregistration.SessionComment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface SessionCommentRepositoryInterface extends CrudRepository<SessionCommentTable, UUID> {

    @Query("SELECT COUNT(c) FROM SessionCommentTable c WHERE c.sessionID = ?1")
    int countBySessionID(UUID sessionID);

    @Query("SELECT AVG(c.rating) FROM SessionCommentTable c WHERE c.sessionID = ?1")
    Double averageRatingBySessionID(UUID sessionID);

    @Query("SELECT AVG(c.rating) FROM SessionCommentTable c")
    Double averageRatingForAllComments();

    @Query("SELECT c FROM SessionCommentTable c WHERE c.sessionID = ?1")
    List<SessionCommentTable> findBySessionID(UUID sessionID);

    List<SessionCommentTable> findByClubId(UUID clubId);

    @Query("SELECT COUNT(c) FROM SessionCommentTable c WHERE c.clubId = ?1")
    int countByClubId(UUID clubId);

    @Query("SELECT AVG(c.rating) FROM SessionCommentTable c WHERE c.clubId = ?1")
    Double averageRatingByClubId(UUID clubId);

}
