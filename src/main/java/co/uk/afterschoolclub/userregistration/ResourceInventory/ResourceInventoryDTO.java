package co.uk.afterschoolclub.userregistration.ResourceInventory;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResourceInventoryDTO {
    private UUID inventoryID;
    private UUID resourceID;
    private Integer quantityAvailable;
}
