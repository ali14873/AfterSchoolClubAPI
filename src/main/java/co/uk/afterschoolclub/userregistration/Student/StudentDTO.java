package co.uk.afterschoolclub.userregistration.Student;

import lombok.*;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentDTO {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String yearGroup;
}
