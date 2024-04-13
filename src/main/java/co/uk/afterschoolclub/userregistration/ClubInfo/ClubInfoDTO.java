package co.uk.afterschoolclub.userregistration.ClubInfo;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClubInfoDTO {
    private UUID clubID;
    private String clubName;
    private String description;
    private String location;
    private Double averageRating;
}
