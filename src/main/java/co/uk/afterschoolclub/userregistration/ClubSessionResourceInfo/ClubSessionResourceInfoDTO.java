package co.uk.afterschoolclub.userregistration.ClubSessionResourceInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubSessionResourceInfoDTO {

    private UUID clubId;
    private String clubName;
    private String description;
    private String location;

    private UUID sessionId;
    private Date sessionDate;
    private String sessionDescription;
    private Date startTime;
    private Date endTime;
    private Boolean isRecurring;
    private String recurrenceRule;
    private String sessionName;

    private UUID resourceId;
    private String resourceName;
    private String resourceDescription;

    private Integer quantityAllocated;
    private Integer quantityAvailable;
    private Integer quantityUsed;

}
