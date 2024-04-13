package co.uk.afterschoolclub.userregistration.ResourceSummaryInfo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import net.jcip.annotations.Immutable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "resource_usage_summary")
@Immutable
public class ResourceSummaryInfoView {

    @Id
    @Column(name = "inventoryid")
    private UUID inventoryId;

    @Column(name = "quantity_available")
    private BigDecimal quantityAvailable;

    @Column(name = "inventory_resourceid")
    private UUID inventoryResourceId;

    @Column(name = "resource_description")
    private String resourceDescription;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "usageid")
    private UUID usageId;

    @Column(name = "quantity_used")
    private BigDecimal quantityUsed;

    @Column(name = "session_completionid")
    private UUID sessionCompletionId;

    @Column(name = "completion_date")
    private Date completionDate;


}
