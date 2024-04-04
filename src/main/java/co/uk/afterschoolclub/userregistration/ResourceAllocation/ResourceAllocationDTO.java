package co.uk.afterschoolclub.userregistration.ResourceAllocation;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceAllocationDTO {

    private UUID allocationID;
    private UUID sessionID;
    private UUID resourceID;
    private Integer quantityAllocated;

}
