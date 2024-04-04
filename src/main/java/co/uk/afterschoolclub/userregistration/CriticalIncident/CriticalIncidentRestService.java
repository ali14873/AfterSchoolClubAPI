package co.uk.afterschoolclub.userregistration.CriticalIncident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/criticalIncidents")
public class CriticalIncidentRestService {

    private final CriticalIncidentApplicationService criticalIncidentApplicationService;

    @Autowired
    public CriticalIncidentRestService(CriticalIncidentApplicationService criticalIncidentApplicationService) {
        this.criticalIncidentApplicationService = criticalIncidentApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<CriticalIncidentDTO> createCriticalIncident(@RequestBody CriticalIncidentDTO criticalIncidentDTO) {
        try {
            CriticalIncidentDTO savedCriticalIncident = criticalIncidentApplicationService.createCriticalIncident(criticalIncidentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCriticalIncident);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CriticalIncidentDTO>> getAllCriticalIncidents() {
        try {
            List<CriticalIncidentDTO> criticalIncidents = criticalIncidentApplicationService.getAllCriticalIncidents();
            return ResponseEntity.status(HttpStatus.OK).body(criticalIncidents);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CriticalIncidentDTO> getCriticalIncidentById(@PathVariable UUID id) {
        try {
            CriticalIncidentDTO criticalIncident = criticalIncidentApplicationService.getCriticalIncidentById(id);
            return ResponseEntity.status(HttpStatus.OK).body(criticalIncident);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CriticalIncidentDTO> updateCriticalIncident(@PathVariable UUID id, @RequestBody CriticalIncidentDTO criticalIncidentDTO) {
        try {
            CriticalIncidentDTO updatedCriticalIncident = criticalIncidentApplicationService.updateCriticalIncident(id, criticalIncidentDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updatedCriticalIncident);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCriticalIncident(@PathVariable UUID id) {
        try {
            criticalIncidentApplicationService.deleteCriticalIncident(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
