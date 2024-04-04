package co.uk.afterschoolclub.userregistration.Resource;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ResourceRepositoryInterface extends CrudRepository<ResourceTable, UUID> {
}
