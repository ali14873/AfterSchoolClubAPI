package co.uk.afterschoolclub.userregistration.ClubSessionResourceInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubSessionResourceInfoApplicationService {

    private final ClubSessionResourceInfoRepositoryInterface repository;

    @Autowired
    public ClubSessionResourceInfoApplicationService(ClubSessionResourceInfoRepositoryInterface repository) {
        this.repository = repository;
    }



    public Long getTotalResourceQuantity() {
        // Implement logic to query total quantity of all resources available
        return repository.sumQuantityAvailable();
    }

    public Long getResourcesUsedInLast30Days() {
        // Implement logic to query total resources used in the last 30 days
        return repository.sumResourcesUsedInLast30Days();
    }
}
