package co.uk.afterschoolclub.userregistration.SessionResourceUsage;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SessionResourceUsageDTO {
    private UUID usageID;
    private UUID sessionCompletionID;
    private UUID resourceID;
    private Integer quantityUsed;
}
