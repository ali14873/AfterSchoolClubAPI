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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sessionTable")
public class SessionTable {

    @Id
    @Column(name = "SessionID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @JoinColumn(name = "ClubID")
    private UUID ClubID;

    @Size(max = 255)
    @Nationalized
    @Column(name = "SessionName", length = 255)
    private String SessionName;

    @Size(max = 255)
    @Nationalized
    @Column(name = "Description", length = 255)
    private String Description;

    @Column(name = "Date")
    private LocalDate Date;

    @Column(name = "StartTime")
    private Time StartTime;

    @Column(name = "EndTime")
    private Time EndTime;

    @Size(max = 255)
    @Column(name = "Location")
    private String Location;

    @Column(name = "IsRecurring")
    private Boolean IsRecurring;

    @Size(max = 255)
    @Column(name = "RecurrenceRule")
    private String RecurrenceRule;

}
