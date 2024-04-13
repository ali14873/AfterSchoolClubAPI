package co.uk.afterschoolclub.userregistration.HealthRecord;

import co.uk.afterschoolclub.userregistration.EmergencyContact.EmergencyContactDTO;
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
    @PutMapping("/edit/{id}")
    public ResponseEntity<HealthRecordDTO> editEmergencyContact(@PathVariable UUID id, @RequestBody HealthRecordDTO healthRecordDTO) {
        try {
            HealthRecordDTO updatedHealthRecord = healthRecordApplicationService.editHealthRecord(id, healthRecordDTO);
            return ResponseEntity.ok(updatedHealthRecord);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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
    @GetMapping("/getByStudentId/{studentId}")
    public ResponseEntity<List<HealthRecordDTO>> getHealthRecordsByStudentId(@PathVariable UUID studentId) {
        try {
            List<HealthRecordDTO> records = healthRecordApplicationService.getHealthRecordsByStudentId(studentId);
            return ResponseEntity.ok(records);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/countOfHealthRecords")
    public ResponseEntity<Long> getCountOfHealthRecords() {
        long count = healthRecordApplicationService.getCountOfHealthRecords();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/countOfIncompleteHealthRecords")
    public ResponseEntity<Long> getCountOfIncompleteHealthRecords() {
        long count = healthRecordApplicationService.getCountOfIncompleteHealthRecords();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/countStudentsWithRecords")
    public ResponseEntity<Long> getCountOfStudentsWithAtLeastOneHealthRecord() {
        long count = healthRecordApplicationService.getCountOfStudentsWithAtLeastOneHealthRecord();
        return ResponseEntity.ok(count);
    }

}
