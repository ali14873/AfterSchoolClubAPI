package co.uk.afterschoolclub.userregistration.HealthRecord;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HealthRecordApplicationService {

    @Autowired
    HealthRecordRepoInterface healthRecordRepoInterface;

    public HealthRecordDTO createHealthRecord(HealthRecordDTO request) {
        HealthRecordTable healthRecord = HealthRecordTable.builder()
                .studentID(request.getStudentID())
                .condition(request.getCondition())
                .details(request.getDetails())
                .medication(request.getMedication())
                .build();
        healthRecordRepoInterface.save(healthRecord);

        return request; // Assuming transformation back to DTO is handled appropriately
    }

    public List<HealthRecordDTO> getAllHealthRecords() {
        List<HealthRecordDTO> healthRecords = new ArrayList<>();
        for(HealthRecordTable record : healthRecordRepoInterface.findAll()) {
            HealthRecordDTO dto = HealthRecordDTO.builder()
                    .id(record.getId())
                    .studentID(record.getStudentID()) // Ensure proper conversion if necessary
                    .condition(record.getCondition())
                    .details(record.getDetails())
                    .medication(record.getMedication())
                    .build();
            healthRecords.add(dto);
        }
        return healthRecords;
    }

    public HealthRecordDTO getHealthRecordById(UUID id) {
        Optional<HealthRecordTable> recordOptional = healthRecordRepoInterface.findById(id);

        if (recordOptional.isPresent()) {
            HealthRecordTable record = recordOptional.get();
            return HealthRecordDTO.builder()
                    .id(record.getId())
                    .studentID(record.getStudentID()) // Ensure proper conversion if necessary
                    .condition(record.getCondition())
                    .details(record.getDetails())
                    .medication(record.getMedication())
                    .build();
        } else {
            throw new EntityNotFoundException("HealthRecord not found with ID: " + id);
        }
    }

    public void deleteHealthRecordById(UUID id) {
        Optional<HealthRecordTable> recordOptional = healthRecordRepoInterface.findById(id);
        if (recordOptional.isPresent()) {
            healthRecordRepoInterface.delete(recordOptional.get());
        } else {
            throw new EntityNotFoundException("HealthRecord not found with ID: " + id);
        }
    }

    public HealthRecordDTO editHealthRecord(UUID id, HealthRecordDTO healthRecordDTO) {

        Optional<HealthRecordTable> healthRecordOptional = healthRecordRepoInterface.findById(id);
        if (healthRecordOptional.isEmpty()) {
            throw new EntityNotFoundException("HealthRecord not found with ID: " + id);
        }

        HealthRecordTable healthRecord = healthRecordOptional.get();
        healthRecord.setStudentID(healthRecordDTO.getStudentID());
        healthRecord.setCondition(healthRecordDTO.getCondition());
        healthRecord.setDetails(healthRecordDTO.getDetails());
        healthRecord.setMedication(healthRecordDTO.getMedication());

        healthRecordRepoInterface.save(healthRecord);

        return healthRecordDTO;

    }

    public List<HealthRecordDTO> getHealthRecordsByStudentId(UUID studentId) {
        List<HealthRecordTable> records = healthRecordRepoInterface.findByStudentID(studentId);
        return records.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public long getCountOfHealthRecords() {
        return healthRecordRepoInterface.count();
    }

    public long getCountOfIncompleteHealthRecords() {
        return healthRecordRepoInterface.countIncompleteHealthRecords();
    }

    private HealthRecordDTO convertToDTO(HealthRecordTable record) {
        return HealthRecordDTO.builder()
                .id(record.getId())
                .studentID(record.getStudentID())
                .condition(record.getCondition())
                .details(record.getDetails())
                .medication(record.getMedication())
                .build();
    }

    public long getCountOfStudentsWithAtLeastOneHealthRecord() {
        return healthRecordRepoInterface.countStudentsWithHealthRecords();
    }

}
