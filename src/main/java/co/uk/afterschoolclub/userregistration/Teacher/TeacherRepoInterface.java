package co.uk.afterschoolclub.userregistration.Teacher;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeacherRepoInterface extends CrudRepository<TeacherTable, UUID> {
}
