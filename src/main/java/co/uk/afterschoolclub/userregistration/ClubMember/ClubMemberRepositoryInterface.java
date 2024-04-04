package co.uk.afterschoolclub.userregistration.ClubMember;

import co.uk.afterschoolclub.userregistration.Club.ClubTable;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClubMemberRepositoryInterface extends CrudRepository<ClubMemberTable, UUID> {



}
