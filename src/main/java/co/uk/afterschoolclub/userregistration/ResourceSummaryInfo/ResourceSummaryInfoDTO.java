package co.uk.afterschoolclub.userregistration.ResourceSummaryInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceSummaryInfoDTO {

    private UUID inventoryId;
    private BigDecimal quantityAvailable;
    private UUID inventoryResourceId;
    private String resourceDescription;
    private String resourceName;
    private UUID usageId;
    private BigDecimal quantityUsed;
    private UUID sessionCompletionId;

    // Additional fields and methods as required

}
