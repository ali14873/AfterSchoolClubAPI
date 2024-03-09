package co.uk.afterschoolclub.userregistration.EmergencyContact;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EmergencyContactRepoInterface extends CrudRepository<EmergencyContactTable, UUID> {
}
