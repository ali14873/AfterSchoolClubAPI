package co.uk.afterschoolclub.userregistration.StudentEmergencyInfo;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentEmergencyInfoDTO {

    private UUID studentId;
    private String studentFirstName;
    private String studentLastName;
    private String contactFirstName;
    private String contactLastName;
    private String phone;
    private String email;
    private String relationship;
}
