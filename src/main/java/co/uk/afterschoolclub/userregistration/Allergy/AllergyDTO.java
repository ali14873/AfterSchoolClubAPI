package co.uk.afterschoolclub.userregistration.Allergy;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AllergyDTO {

    private UUID id;
    private UUID studentId;
    private String allergyType;
    private String severity;
    private String actionType;
    private String action;
}
