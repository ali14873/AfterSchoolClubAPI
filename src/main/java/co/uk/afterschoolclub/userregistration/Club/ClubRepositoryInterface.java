package co.uk.afterschoolclub.userregistration.Club;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClubRepositoryInterface extends CrudRepository<ClubTable, UUID> {
}
