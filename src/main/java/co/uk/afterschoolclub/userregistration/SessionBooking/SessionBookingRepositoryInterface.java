package co.uk.afterschoolclub.userregistration.SessionBooking;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SessionBookingRepositoryInterface extends CrudRepository<SessionBookingTable, UUID> {
}
