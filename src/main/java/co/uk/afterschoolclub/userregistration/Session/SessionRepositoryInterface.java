package co.uk.afterschoolclub.userregistration.Session;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface SessionRepositoryInterface extends CrudRepository<SessionTable, UUID> {

    List<SessionTable> findByClubID(UUID clubID);

}
