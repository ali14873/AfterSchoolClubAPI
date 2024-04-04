package co.uk.afterschoolclub.userregistration.Teacher;

import lombok.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TeacherDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String roleType;
}
