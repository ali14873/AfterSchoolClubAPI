package co.uk.afterschoolclub.userregistration.StudentParent;

import co.uk.afterschoolclub.userregistration.Parent.ParentTable;
import co.uk.afterschoolclub.userregistration.Student.StudentTable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "StudentParentTable")
public class StudentParentTable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "StudentParentID", nullable = false)
    private UUID id;


    @Column(name = "StudentID", nullable = false)
    private UUID studentID;


    @Column(name = "ParentID", nullable = false)
    private UUID parentID;

    @Size(max = 50)
    @Column(name = "RelationshipType", length = 50)
    private String relationshipType;

}