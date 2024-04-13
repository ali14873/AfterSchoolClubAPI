package co.uk.afterschoolclub.userregistration.HealthRecord;

import co.uk.afterschoolclub.userregistration.Student.StudentTable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "HealthRecordTable")
public class HealthRecordTable    {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "HealthRecordID", nullable = false)
    private UUID id;

    @NotNull
    @JoinColumn(name = "StudentID", nullable = false)
    private UUID studentID;

    @Size(max = 255)
    @Column(name = "Condition")
    private String condition;

    @Size(max = 255)
    @Column(name = "Details")
    private String details;

    @Size(max = 255)
    @Column(name = "Medication")
    private String medication;


}