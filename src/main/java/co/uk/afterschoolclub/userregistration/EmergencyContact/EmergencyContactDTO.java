package co.uk.afterschoolclub.userregistration.EmergencyContact;

import co.uk.afterschoolclub.userregistration.Student.StudentTable;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmergencyContactDTO {

    private UUID id;



    private UUID studentID;
    private String firstName;
    private String lastName;
    private String relationship;
    private String phone;
    private String email;
}
