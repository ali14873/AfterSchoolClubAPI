package co.uk.afterschoolclub.userregistration.Club;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClubDTO {
    private UUID id;
    private String clubName;
    private String description;
    private String location;
}
