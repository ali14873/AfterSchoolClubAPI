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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "StudentID", nullable = false)
    private StudentTable studentID;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ParentID", nullable = false)
    private ParentTable parentID;

    @Size(max = 50)
    @Column(name = "RelationshipType", length = 50)
    private String relationshipType;

}