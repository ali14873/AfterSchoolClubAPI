package co.uk.afterschoolclub.userregistration.ResourceAllocation;


import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ResourceAllocationRepositoryInterface extends CrudRepository<ResourceAllocationTable, UUID> {
}
