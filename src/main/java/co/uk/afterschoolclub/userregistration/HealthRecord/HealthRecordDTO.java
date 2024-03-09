package co.uk.afterschoolclub.userregistration.HealthRecord;

import co.uk.afterschoolclub.userregistration.Student.StudentTable;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HealthRecordDTO {
    private UUID id;
    private StudentTable studentID;
    private String condition;
    private String details;
    private String medication;

}
