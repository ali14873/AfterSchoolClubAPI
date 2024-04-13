package co.uk.afterschoolclub.userregistration.Student;

import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String status;
    private LocalDate dateOfBirth;
    private String gender;
    private String yearGroup;
}
