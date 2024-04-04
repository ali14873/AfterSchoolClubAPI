package co.uk.afterschoolclub.userregistration.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ResourceApplicationService {

    private final ResourceRepositoryInterface resourceRepository;

    @Autowired
    public ResourceApplicationService(ResourceRepositoryInterface resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Transactional
    public ResourceDTO createResource(ResourceDTO resourceDTO) {
        ResourceTable resource = ResourceTable.builder()
                .resourceName(resourceDTO.getResourceName())
                .description(resourceDTO.getDescription())
                .build();

        resource = resourceRepository.save(resource);
        return ResourceDTO.builder()
                .resourceId(resource.getResourceId())
                .resourceName(resource.getResourceName())
                .description(resource.getDescription())
                .build();
    }

    public List<ResourceDTO> getAllResources() {
        List<ResourceTable> resources = StreamSupport.stream(resourceRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return resources.stream()
                .map(resource -> ResourceDTO.builder()
                        .resourceId(resource.getResourceId())
                        .resourceName(resource.getResourceName())
                        .description(resource.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    public ResourceDTO getResourceById(UUID id) {
        Optional<ResourceTable> resourceOptional = resourceRepository.findById(id);

        if (resourceOptional.isPresent()) {
            ResourceTable resource = resourceOptional.get();
            return ResourceDTO.builder()
                    .resourceId(resource.getResourceId())
                    .resourceName(resource.getResourceName())
                    .description(resource.getDescription())
                    .build();
        } else {
            throw new RuntimeException("Resource not found with id: " + id);
        }
    }

    public ResourceDTO updateResource(UUID id, ResourceDTO resourceDTO) {
        Optional<ResourceTable> resourceOptional = resourceRepository.findById(id);

        if (resourceOptional.isPresent()) {
            ResourceTable resource = resourceOptional.get();
            resource.setResourceName(resourceDTO.getResourceName());
            resource.setDescription(resourceDTO.getDescription());

            resource = resourceRepository.save(resource);
            return ResourceDTO.builder()
                    .resourceId(resource.getResourceId())
                    .resourceName(resource.getResourceName())
                    .description(resource.getDescription())
                    .build();
        } else {
            throw new RuntimeException("Resource not found with id: " + id);
        }
    }

    public void deleteResource(UUID id) {
        Optional<ResourceTable> resourceOptional = resourceRepository.findById(id);

        if (resourceOptional.isPresent()) {
            resourceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Resource not found with id: " + id);
        }
    }
}
