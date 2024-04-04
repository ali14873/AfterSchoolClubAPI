package co.uk.afterschoolclub.userregistration.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/resources")
public class ResourceRestService {

    private final ResourceApplicationService resourceApplicationService;

    @Autowired
    public ResourceRestService(ResourceApplicationService resourceApplicationService) {
        this.resourceApplicationService = resourceApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResourceDTO> createResource(@RequestBody ResourceDTO resourceDTO) {
        ResourceDTO createdResource = resourceApplicationService.createResource(resourceDTO);
        return new ResponseEntity<>(createdResource, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ResourceDTO>> getAllResources() {
        List<ResourceDTO> resources = resourceApplicationService.getAllResources();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResourceDTO> getResourceById(@PathVariable UUID id) {
        try {
            ResourceDTO resource = resourceApplicationService.getResourceById(id);
            return new ResponseEntity<>(resource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ResourceDTO> updateResource(@PathVariable UUID id, @RequestBody ResourceDTO resourceDTO) {
        try {
            ResourceDTO updatedResource = resourceApplicationService.updateResource(id, resourceDTO);
            return new ResponseEntity<>(updatedResource, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable UUID id) {
        try {
            resourceApplicationService.deleteResource(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
