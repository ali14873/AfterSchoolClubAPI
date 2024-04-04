package co.uk.afterschoolclub.userregistration.SessionBooking;

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
@Table(name = "sessionBookingTable")
public class SessionBookingTable {

    @Id
    @Column(name = "BookingID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @JoinColumn(name = "SessionID")
    private UUID SessionID;

    @NotNull
    @Column(name = "UserID")
    private UUID UserID;

    @Size(max = 255)
    @Column(name = "BookingType")
    private String BookingType;

    @Size(max = 255)
    @Column(name = "Status")
    private String Status;

  }
