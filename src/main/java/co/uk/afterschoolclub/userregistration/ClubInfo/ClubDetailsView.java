package co.uk.afterschoolclub.userregistration.ClubInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.util.UUID;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@NoArgsConstructor
@Immutable
@Entity
@Table(name = "club_info_view_1")
public class ClubDetailsView {
    @Id
    @NotNull
    @Column(name = "clubid", nullable = false)
    private UUID clubid;

    @Size(max = 255)
    @Column(name = "club_name")
    private String clubName;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 255)
    @Column(name = "location")
    private String location;

    @Column(name = "AverageRating")
    private Integer averageRating;

}