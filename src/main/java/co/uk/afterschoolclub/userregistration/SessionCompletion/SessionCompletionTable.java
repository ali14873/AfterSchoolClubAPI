package co.uk.afterschoolclub.userregistration.SessionCompletion;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "SessionCompletion")
public class SessionCompletionTable {

    @Id
    @Column(name = "SessionCompletionID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID sessionCompletionID;

    @Column(name = "SessionID")
    private UUID sessionID;

    @Column(name = "CompletionDate")
    @Temporal(TemporalType.DATE)
    private Date completionDate;

    @Column(name = "Notes")
    private String notes;

    // Additional methods and constructors can go here as needed
}
