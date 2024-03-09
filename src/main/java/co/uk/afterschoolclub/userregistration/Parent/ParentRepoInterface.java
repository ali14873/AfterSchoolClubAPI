package co.uk.afterschoolclub.userregistration.Parent;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ParentRepoInterface extends CrudRepository<ParentTable, UUID> {
}
