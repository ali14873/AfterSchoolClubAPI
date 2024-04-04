package co.uk.afterschoolclub.userregistration.SessionCompletion;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SessionCompletionDTO {

    private UUID sessionCompletionID;
    private UUID sessionID;
    private Date completionDate;
    private String notes;

}
