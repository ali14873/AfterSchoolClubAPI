package co.uk.afterschoolclub.userregistration.Resource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Resources")
public class ResourceTable {

    @Id
    @Column(name = "ResourceID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID resourceId;

    @Column(name = "ResourceName")
    private String resourceName;

    @Column(name = "Description")
    private String description;

}
