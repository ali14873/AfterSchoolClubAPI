package co.uk.afterschoolclub.userregistration.Allergy;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Allergies")
public class AllergyTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "StudentID")
    private UUID studentId;

    @Column(name = "AllergyType")
    private String allergyType;

    @Column(name = "Severity")
    private String severity;

    @Column(name = "ActionType")
    private String actionType;

    @Column(name = "Action")
    private String action;
}
