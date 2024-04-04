package co.uk.afterschoolclub.userregistration.ResourceInventory;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ResourceInventory")
public class ResourceInventoryTable {

    @Id
    @Column(name = "InventoryID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID inventoryID;

    @Column(name = "ResourceID")
    private UUID resourceID;

    @Column(name = "QuantityAvailable")
    private Integer quantityAvailable;
}
