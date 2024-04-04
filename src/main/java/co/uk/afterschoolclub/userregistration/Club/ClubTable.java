package co.uk.afterschoolclub.userregistration.Club;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Clubs")
public class ClubTable {
    @Id
    @Column(name = "ClubID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Size(max = 255)
    @Column(name = "ClubName")
    private String clubName;

    @Size(max = 255)
    @Column(name = "Description")
    private String description;

    @Size(max = 255)
    @Column(name = "Location")
    private String location;
}
