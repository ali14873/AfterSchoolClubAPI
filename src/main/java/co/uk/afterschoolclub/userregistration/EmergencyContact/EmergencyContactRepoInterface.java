package co.uk.afterschoolclub.userregistration.EmergencyContact;

import co.uk.afterschoolclub.userregistration.SessionBooking.SessionBookingTable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface EmergencyContactRepoInterface extends CrudRepository<EmergencyContactTable, UUID> {
    List<EmergencyContactTable> findBystudentID(UUID ID);
}
