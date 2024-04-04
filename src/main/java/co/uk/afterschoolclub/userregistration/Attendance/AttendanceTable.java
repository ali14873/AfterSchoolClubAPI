package co.uk.afterschoolclub.userregistration.Attendance;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "AttendanceRecords")
public class AttendanceTable {

    @Id
    @Column(name = "AttendanceID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Size(max = 255)
    @Column(name = "BookingID")
    private UUID bookingId;

    @Size(max = 255)
    @Column(name = "SessionID")
    private UUID sessionId;

    @Size(max = 255)
    @Column(name = "UserID")
    private UUID userId;


    @Size(max = 255)
    @Column(name = "AttendanceStatus")
    private String attendanceStatus;
}
