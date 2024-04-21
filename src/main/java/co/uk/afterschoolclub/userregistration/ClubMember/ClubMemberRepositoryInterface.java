package co.uk.afterschoolclub.userregistration.ClubMember;

import co.uk.afterschoolclub.userregistration.Club.ClubTable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ClubMemberRepositoryInterface extends CrudRepository<ClubMemberTable, UUID> {

    List<ClubMemberTable> findByClubId(UUID clubId);
    List<ClubMemberTable> findByUserId(UUID userId);

}
