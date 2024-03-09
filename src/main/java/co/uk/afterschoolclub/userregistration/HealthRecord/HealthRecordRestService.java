package co.uk.afterschoolclub.userregistration.HealthRecord;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/healthRecord")
public class HealthRecordRestService {

    final HealthRecordApplicationService healthRecordApplicationService;

    @Autowired
    public HealthRecordRestService(HealthRecordApplicationService healthRecordApplicationService) {
        this.healthRecordApplicationService = healthRecordApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<HealthRecordDTO> createHealthRecord(@RequestBody final HealthRecordDTO request) {
        HealthRecordDTO healthRecord = healthRecordApplicationService.createHealthRecord(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(healthRecord);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<HealthRecordDTO>> getAllHealthRecords() {
        List<HealthRecordDTO> healthRecordDTOList = healthRecordApplicationService.getAllHealthRecords();
        return ResponseEntity.status(HttpStatus.OK).body(healthRecordDTOList);
    }

    @GetMapping("/getHealthRecord/{id}")
    public ResponseEntity<HealthRecordDTO> getHealthRecordById(@PathVariable UUID id) {
        try {
            HealthRecordDTO healthRecordDTO = healthRecordApplicationService.getHealthRecordById(id);
            return ResponseEntity.status(HttpStatus.OK).body(healthRecordDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHealthRecordById(@PathVariable UUID id) {
        try {
            healthRecordApplicationService.deleteHealthRecordById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Assuming CSV upload isn't needed for HealthRecords as it wasn't specified.
}
