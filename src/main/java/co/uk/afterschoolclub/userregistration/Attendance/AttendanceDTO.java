package co.uk.afterschoolclub.userregistration.Attendance;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AttendanceDTO {

    private UUID id;
    private UUID bookingId;
    private UUID sessionId;
    private UUID userId;
    private String attendanceStatus;
}
