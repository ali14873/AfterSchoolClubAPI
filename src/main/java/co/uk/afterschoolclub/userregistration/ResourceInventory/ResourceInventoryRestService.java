package co.uk.afterschoolclub.userregistration.ResourceInventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/resourceInventory")
public class ResourceInventoryRestService {

    private final ResourceInventoryApplicationService resourceInventoryApplicationService;

    @Autowired
    public ResourceInventoryRestService(ResourceInventoryApplicationService resourceInventoryApplicationService) {
        this.resourceInventoryApplicationService = resourceInventoryApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResourceInventoryDTO> createResourceInventory(@RequestBody ResourceInventoryDTO resourceInventoryDTO) {
        ResourceInventoryDTO createdResourceInventory = resourceInventoryApplicationService.createResourceInventory(resourceInventoryDTO);
        return new ResponseEntity<>(createdResourceInventory, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ResourceInventoryDTO>> getAllResourceInventories() {
        List<ResourceInventoryDTO> resourceInventories = resourceInventoryApplicationService.getAllResourceInventories();
        return new ResponseEntity<>(resourceInventories, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResourceInventoryDTO> getResourceInventoryById(@PathVariable UUID id) {
        try {
            ResourceInventoryDTO resourceInventory = resourceInventoryApplicationService.getResourceInventoryById(id);
            return ResponseEntity.ok(resourceInventory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ResourceInventoryDTO> updateResourceInventory(@PathVariable UUID id, @RequestBody ResourceInventoryDTO resourceInventoryDTO) {
        try {
            ResourceInventoryDTO updatedResourceInventory = resourceInventoryApplicationService.updateResourceInventory(id, resourceInventoryDTO);
            return ResponseEntity.ok(updatedResourceInventory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResourceInventory(@PathVariable UUID id) {
        try {
            resourceInventoryApplicationService.deleteResourceInventoryById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
