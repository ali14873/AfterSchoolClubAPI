package co.uk.afterschoolclub.userregistration.ClubSessionResourceInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clubSessionResourceInfo")
public class ClubSessionResourceInfoRestService {

    private final ClubSessionResourceInfoApplicationService service;

    @Autowired
    public ClubSessionResourceInfoRestService(ClubSessionResourceInfoApplicationService service) {
        this.service = service;
    }

    @GetMapping("/totalResourceQuantity")
    public ResponseEntity<Long> getTotalResourceQuantity() {
        Long totalQuantity = service.getTotalResourceQuantity();
        return ResponseEntity.ok(totalQuantity);
    }

    @GetMapping("/resourcesUsedLast30Days")
    public ResponseEntity<Long> getResourcesUsedInLast30Days() {
        Long resourcesUsed = service.getResourcesUsedInLast30Days();
        return ResponseEntity.ok(resourcesUsed);
    }
}
