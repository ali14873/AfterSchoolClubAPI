package co.uk.afterschoolclub.userregistration.ResourceSummaryInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource-summary")
public class ResourceSummaryInfoRestService {

    private final ResourceSummaryInfoService resourceSummaryInfoService;

    @Autowired
    public ResourceSummaryInfoRestService(ResourceSummaryInfoService resourceSummaryInfoService) {
        this.resourceSummaryInfoService = resourceSummaryInfoService;
    }

    @GetMapping("/total-quantity-available")
    public ResponseEntity<Long> getTotalQuantityOfResourcesAvailable() {
        return ResponseEntity.ok(resourceSummaryInfoService.getTotalQuantityOfResourcesAvailable());
    }

    @GetMapping("/total-resources")
    public ResponseEntity<Long> getTotalDistinctResources() {
        return ResponseEntity.ok(resourceSummaryInfoService.getTotalDistinctResources());
    }

    @GetMapping("/quantity-used-last-month")
    public ResponseEntity<Long> getQuantityOfResourcesUsedInTheLastMonth() {
        return ResponseEntity.ok(resourceSummaryInfoService.getQuantityOfResourcesUsedInTheLastMonth());
    }
}
