package co.uk.afterschoolclub.userregistration.Session;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("title")  // This changes the JSON field name to "title"
    private String sessionName;

    private String description;
    private LocalDate date;

    private String location;
    private Boolean isRecurring;
    private RecurringDTO recurring; // Adjusted to use nested DTO
}

