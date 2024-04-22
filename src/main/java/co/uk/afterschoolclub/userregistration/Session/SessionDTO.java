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
    private UUID clubID;
    private String sessionName;
    private String description;
    private LocalDate date;
    private Time startTime;
    private Time endTime;
    private String location;
    private Boolean isRecurring;
    private String recurrenceType;
    private String recurrenceDaysOfWeek; // "MON,TUE", etc.
    private Integer recurrenceDayOfMonth; // 1-31
    private String recurrenceMonthsOfYear; // "JAN,FEB,MAR", etc.
    private Integer recurrenceInterval;
    private String recurrenceRule; // This could potentially hold a more complex recurrence rule if needed
}
