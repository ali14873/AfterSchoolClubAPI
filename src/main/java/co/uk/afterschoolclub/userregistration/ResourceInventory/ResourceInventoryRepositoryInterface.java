package co.uk.afterschoolclub.userregistration.ResourceInventory;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ResourceInventoryRepositoryInterface extends CrudRepository<ResourceInventoryTable, UUID> {
}
