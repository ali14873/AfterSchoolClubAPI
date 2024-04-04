package co.uk.afterschoolclub.userregistration.ResourceInventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ResourceInventoryApplicationService {

    private final ResourceInventoryRepositoryInterface resourceInventoryRepository;

    @Autowired
    public ResourceInventoryApplicationService(ResourceInventoryRepositoryInterface resourceInventoryRepository) {
        this.resourceInventoryRepository = resourceInventoryRepository;
    }

    @Transactional
    public ResourceInventoryDTO createResourceInventory(ResourceInventoryDTO resourceInventoryDTO) {
        ResourceInventoryTable resourceInventory = ResourceInventoryTable.builder()
                .resourceID(resourceInventoryDTO.getResourceID())
                .quantityAvailable(resourceInventoryDTO.getQuantityAvailable())
                .build();

        resourceInventory = resourceInventoryRepository.save(resourceInventory);
        return ResourceInventoryDTO.builder()
                .inventoryID(resourceInventory.getInventoryID())
                .resourceID(resourceInventory.getResourceID())
                .quantityAvailable(resourceInventory.getQuantityAvailable())
                .build();
    }

    public List<ResourceInventoryDTO> getAllResourceInventories() {
        List<ResourceInventoryTable> resourceInventories = StreamSupport
                .stream(resourceInventoryRepository.findAll().spliterator(), false)
                .toList();

        return resourceInventories.stream()
                .map(resourceInventory -> ResourceInventoryDTO.builder()
                        .inventoryID(resourceInventory.getInventoryID())
                        .resourceID(resourceInventory.getResourceID())
                        .quantityAvailable(resourceInventory.getQuantityAvailable())
                        .build())
                .collect(Collectors.toList());
    }

    public ResourceInventoryDTO getResourceInventoryById(UUID id) {
        Optional<ResourceInventoryTable> resourceInventoryOptional = resourceInventoryRepository.findById(id);

        if (resourceInventoryOptional.isPresent()) {
            ResourceInventoryTable resourceInventory = resourceInventoryOptional.get();
            return ResourceInventoryDTO.builder()
                    .inventoryID(resourceInventory.getInventoryID())
                    .resourceID(resourceInventory.getResourceID())
                    .quantityAvailable(resourceInventory.getQuantityAvailable())
                    .build();
        } else {
            throw new RuntimeException("Resource Inventory not found with id: " + id);
        }
    }

    public ResourceInventoryDTO updateResourceInventory(UUID id, ResourceInventoryDTO resourceInventoryDTO) {
        Optional<ResourceInventoryTable> resourceInventoryOptional = resourceInventoryRepository.findById(id);

        if (resourceInventoryOptional.isPresent()) {
            ResourceInventoryTable resourceInventory = resourceInventoryOptional.get();
            resourceInventory.setResourceID(resourceInventoryDTO.getResourceID());
            resourceInventory.setQuantityAvailable(resourceInventoryDTO.getQuantityAvailable());

            resourceInventory = resourceInventoryRepository.save(resourceInventory);
            return ResourceInventoryDTO.builder()
                    .inventoryID(resourceInventory.getInventoryID())
                    .resourceID(resourceInventory.getResourceID())
                    .quantityAvailable(resourceInventory.getQuantityAvailable())
                    .build();
        } else {
            throw new RuntimeException("Resource Inventory not found with id: " + id);
        }
    }

    public void deleteResourceInventoryById(UUID id) {
        Optional<ResourceInventoryTable> resourceInventoryOptional = resourceInventoryRepository.findById(id);

        if (resourceInventoryOptional.isPresent()) {
            resourceInventoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Resource Inventory not found with id: " + id);
        }
    }
}
