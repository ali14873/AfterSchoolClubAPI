package co.uk.afterschoolclub.userregistration.ResourceAllocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/resourceAllocations")
public class ResourceAllocationRestService {

    private final ResourceAllocationApplicationService resourceAllocationApplicationService;

    @Autowired
    public ResourceAllocationRestService(ResourceAllocationApplicationService resourceAllocationApplicationService) {
        this.resourceAllocationApplicationService = resourceAllocationApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResourceAllocationDTO> createResourceAllocation(@RequestBody ResourceAllocationDTO resourceAllocationDTO) {
        ResourceAllocationDTO createdResourceAllocation = resourceAllocationApplicationService.createResourceAllocation(resourceAllocationDTO);
        return new ResponseEntity<>(createdResourceAllocation, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")

    public ResponseEntity<List<ResourceAllocationDTO>> getAllResourceAllocations() {
        List<ResourceAllocationDTO> resourceAllocations = resourceAllocationApplicationService.getAllResourceAllocations();
        return new ResponseEntity<>(resourceAllocations, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResourceAllocationDTO> getResourceAllocationById(@PathVariable UUID id) {
        ResourceAllocationDTO resourceAllocationDTO = resourceAllocationApplicationService.getResourceAllocationById(id);
        return resourceAllocationDTO != null ? ResponseEntity.ok(resourceAllocationDTO) : ResponseEntity.notFound().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ResourceAllocationDTO> updateResourceAllocation(@PathVariable UUID id, @RequestBody ResourceAllocationDTO resourceAllocationDTO) {
        ResourceAllocationDTO updatedResourceAllocation = resourceAllocationApplicationService.updateResourceAllocation(id, resourceAllocationDTO);
        return updatedResourceAllocation != null ? ResponseEntity.ok(updatedResourceAllocation) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable UUID id) {
        try {
            resourceAllocationApplicationService.deleteResourceAllocation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
