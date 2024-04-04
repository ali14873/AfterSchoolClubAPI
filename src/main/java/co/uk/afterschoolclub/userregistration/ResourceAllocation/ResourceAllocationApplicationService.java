package co.uk.afterschoolclub.userregistration.ResourceAllocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ResourceAllocationApplicationService {

    private final ResourceAllocationRepositoryInterface resourceAllocationRepository;

    @Autowired
    public ResourceAllocationApplicationService(ResourceAllocationRepositoryInterface resourceAllocationRepository) {
        this.resourceAllocationRepository = resourceAllocationRepository;
    }

    @Transactional
    public ResourceAllocationDTO createResourceAllocation(ResourceAllocationDTO resourceAllocationDTO) {
        ResourceAllocationTable resourceAllocation = ResourceAllocationTable.builder()
                .sessionID(resourceAllocationDTO.getSessionID())
                .resourceID(resourceAllocationDTO.getResourceID())
                .quantityAllocated(resourceAllocationDTO.getQuantityAllocated())
                .build();

        resourceAllocation = resourceAllocationRepository.save(resourceAllocation);
        return ResourceAllocationDTO.builder()
                .allocationID(resourceAllocation.getAllocationID())
                .sessionID(resourceAllocation.getSessionID())
                .resourceID(resourceAllocation.getResourceID())
                .quantityAllocated(resourceAllocation.getQuantityAllocated())
                .build();
    }

    public List<ResourceAllocationDTO> getAllResourceAllocations() {
        List<ResourceAllocationTable> resourceAllocations = StreamSupport
                .stream(resourceAllocationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return resourceAllocations.stream()
                .map(resourceAllocation -> ResourceAllocationDTO.builder()
                        .allocationID(resourceAllocation.getAllocationID())
                        .sessionID(resourceAllocation.getSessionID())
                        .resourceID(resourceAllocation.getResourceID())
                        .quantityAllocated(resourceAllocation.getQuantityAllocated())
                        .build())
                .collect(Collectors.toList());
    }

    public ResourceAllocationDTO getResourceAllocationById(UUID id) {
        Optional<ResourceAllocationTable> resourceAllocationOptional = resourceAllocationRepository.findById(id);

        if (resourceAllocationOptional.isPresent()) {
            ResourceAllocationTable resourceAllocation = resourceAllocationOptional.get();
            return ResourceAllocationDTO.builder()
                    .allocationID(resourceAllocation.getAllocationID())
                    .sessionID(resourceAllocation.getSessionID())
                    .resourceID(resourceAllocation.getResourceID())
                    .quantityAllocated(resourceAllocation.getQuantityAllocated())
                    .build();
        } else {
            throw new RuntimeException("Resource Allocation not found with id: " + id);
        }
    }

    public ResourceAllocationDTO updateResourceAllocation(UUID id, ResourceAllocationDTO resourceAllocationDTO) {
        Optional<ResourceAllocationTable> resourceAllocationOptional = resourceAllocationRepository.findById(id);

        if (resourceAllocationOptional.isPresent()) {
            ResourceAllocationTable resourceAllocation = resourceAllocationOptional.get();
            resourceAllocation.setSessionID(resourceAllocationDTO.getSessionID());
            resourceAllocation.setResourceID(resourceAllocationDTO.getResourceID());
            resourceAllocation.setQuantityAllocated(resourceAllocationDTO.getQuantityAllocated());

            resourceAllocation = resourceAllocationRepository.save(resourceAllocation);
            return ResourceAllocationDTO.builder()
                    .allocationID(resourceAllocation.getAllocationID())
                    .sessionID(resourceAllocation.getSessionID())
                    .resourceID(resourceAllocation.getResourceID())
                    .quantityAllocated(resourceAllocation.getQuantityAllocated())
                    .build();
        } else {
            throw new RuntimeException("Resource Allocation not found with id: " + id);
        }
    }

    public void deleteResourceAllocation(UUID id) {
        Optional<ResourceAllocationTable> resourceAllocationOptional = resourceAllocationRepository.findById(id);

        if (resourceAllocationOptional.isPresent()) {
            resourceAllocationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Resource Allocation not found with id: " + id);
        }
    }
}
