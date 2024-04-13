package co.uk.afterschoolclub.userregistration.CriticalIncident;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CriticalIncidentDTO {
    private UUID incidentId;
    private UUID reportedBy;
    private UUID studentId;
    private UUID sessionId;
    private LocalDate incidentDate;
    private String incidentType;
    private String description;
    private String actionTaken;
    private Boolean resolved;
    private String resolutionDetails;
}
