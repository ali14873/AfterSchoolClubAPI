package co.uk.afterschoolclub.userregistration.ResourceSummaryInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceSummaryInfoService {

    private final ResourceSummaryInfoRepository resourceSummaryInfoRepository;

    @Autowired
    public ResourceSummaryInfoService(ResourceSummaryInfoRepository resourceSummaryInfoRepository) {
        this.resourceSummaryInfoRepository = resourceSummaryInfoRepository;
    }

    public Long getTotalQuantityOfResourcesAvailable() {
        return resourceSummaryInfoRepository.totalQuantityOfResourcesAvailable();
    }

    public Long getTotalDistinctResources() {
        return resourceSummaryInfoRepository.totalDistinctResources();
    }

    public Long getQuantityOfResourcesUsedInTheLastMonth() {
        return resourceSummaryInfoRepository.quantityOfResourcesUsedInTheLastMonth();
    }
}
