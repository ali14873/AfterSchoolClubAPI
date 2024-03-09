package co.uk.afterschoolclub.userregistration.Student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepoInterface extends CrudRepository<StudentTable, UUID> {


}
