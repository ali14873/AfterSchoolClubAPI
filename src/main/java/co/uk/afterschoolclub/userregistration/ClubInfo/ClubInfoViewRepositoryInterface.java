package co.uk.afterschoolclub.userregistration.ClubInfo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClubInfoViewRepositoryInterface extends CrudRepository<ClubDetailsView, UUID> {
    // This method retrieves all clubs, assuming ClubInfoView is mapped to a view or table that aggregates club details.


    // Native query to select clubs based on membership status and user ID
    @Query(value = "SELECT c.* FROM ClubInfoView c INNER JOIN club_member cm ON c.ClubID = cm.ClubID WHERE cm.UserId = :userId AND cm.MembershipStatus = 'member'", nativeQuery = true)
    List<ClubDetailsView> findAllByMember(@Param("userId") UUID userId);
}

