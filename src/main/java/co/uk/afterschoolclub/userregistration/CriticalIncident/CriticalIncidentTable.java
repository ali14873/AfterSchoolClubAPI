package co.uk.afterschoolclub.userregistration.CriticalIncident;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "CriticalIncidents")
public class CriticalIncidentTable {

    @Id
    @Column(name = "CriticalIncidentID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Size(max = 255)
    @Column(name = "ReportedBy")
    private UUID reportedBy;

    @Size(max = 255)
    @Column(name = "SessionID")
    private UUID sessionId;

    @Size(max = 255)
    @Column(name = "IncidentDate")
    private LocalDate incidentDate;

    @Size(max = 255)
    @Column(name = "IncidentType")
    private String incidentType;

    @Size(max = 255)
    @Column(name = "Description")
    private String description;

    @Size(max = 255)
    @Column(name = "ActionTaken")
    private String actionTaken;

    @Size(max = 255)
    @Column(name = "Resolved")
    private Boolean resolved;

    @Size(max = 255)
    @Column(name = "ResolutionDetails")
    private String resolutionDetails;

}
