package co.uk.afterschoolclub.userregistration.Session;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionDTO {
    private UUID id;
    private UUID clubID;
    private String sessionName;
    private String description;
    private LocalDate date;
    private Time startTime;
    private Time endTime;
    private String location;
    private Boolean isRecurring;
    private RecurringDTO recurring; // Adjusted to use nested DTO
}

