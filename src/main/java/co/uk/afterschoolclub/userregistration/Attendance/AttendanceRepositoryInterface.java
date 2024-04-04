package co.uk.afterschoolclub.userregistration.Attendance;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AttendanceRepositoryInterface extends CrudRepository<AttendanceTable, UUID> {
}
