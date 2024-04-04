package co.uk.afterschoolclub.userregistration.SessionStaff;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SessionStaffRepositoryInterface extends CrudRepository<SessionStaffTable, UUID> {
}
