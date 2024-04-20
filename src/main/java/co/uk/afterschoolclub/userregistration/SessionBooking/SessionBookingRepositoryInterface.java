package co.uk.afterschoolclub.userregistration.SessionBooking;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface SessionBookingRepositoryInterface extends CrudRepository<SessionBookingTable, UUID> {
    List<SessionBookingTable> findBySessionID(UUID sessionID);
    List<SessionBookingTable> findByUserId(UUID userId);
}
