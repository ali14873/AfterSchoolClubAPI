package co.uk.afterschoolclub.userregistration.StudentParent;

import co.uk.afterschoolclub.userregistration.Parent.ParentTable;
import co.uk.afterschoolclub.userregistration.Student.StudentTable;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentParentDTO {

    private UUID id;
    private UUID studentID;
    private UUID parentID;
    private String relationshipType;

}
