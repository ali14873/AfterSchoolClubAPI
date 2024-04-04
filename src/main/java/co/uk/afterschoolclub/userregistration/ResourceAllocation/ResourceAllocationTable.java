package co.uk.afterschoolclub.userregistration.ResourceAllocation;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ResourceAllocation")
public class ResourceAllocationTable {

    @Id
    @Column(name = "AllocationID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID allocationID;

    @Column(name = "SessionID")
    private UUID sessionID;

    @Column(name = "ResourceID")
    private UUID resourceID;

    @Column(name = "QuantityAllocated")
    private Integer quantityAllocated;
}
