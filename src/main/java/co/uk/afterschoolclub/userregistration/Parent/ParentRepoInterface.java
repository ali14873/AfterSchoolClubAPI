package co.uk.afterschoolclub.userregistration.Parent;

import co.uk.afterschoolclub.userregistration.Teacher.TeacherTable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ParentRepoInterface extends CrudRepository<ParentTable, UUID> {
    Optional<ParentTable> findByEmail(String email);

}
