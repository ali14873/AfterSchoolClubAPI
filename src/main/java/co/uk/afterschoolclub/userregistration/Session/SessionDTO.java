package co.uk.afterschoolclub.userregistration.Session;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SessionDTO {
    private UUID id;
    private UUID ClubID;
    private String SessionName;
    private String Description;
    private LocalDate Date;
    private Time StartTime;
    private Time EndTime;
    private String Location;
    private Boolean IsRecurring;
    private String RecurrenceRule;
}
