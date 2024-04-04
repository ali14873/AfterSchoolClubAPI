package co.uk.afterschoolclub.userregistration.SessionStaff;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "SessionStaff")
public class SessionStaffTable {

    @Id
    @Column(name = "SessionStaffID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID sessionStaffID;

    @Column(name = "SessionID")
    private UUID sessionID;

    @Column(name = "StaffID")
    private UUID staffID;

    @Column(name = "Role")
    private String role;
}
