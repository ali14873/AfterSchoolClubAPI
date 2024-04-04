package co.uk.afterschoolclub.userregistration.SessionResourceUsage;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "SessionResourceUsage")
public class SessionResourceUsageTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UsageID", nullable = false)
    private UUID usageID;

    @Column(name = "SessionCompletionID")
    private UUID sessionCompletionID;

    @Column(name = "ResourceID")
    private UUID resourceID;

    @Column(name = "QuantityUsed")
    private Integer quantityUsed;
}
