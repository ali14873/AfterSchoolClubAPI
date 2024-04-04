package co.uk.afterschoolclub.userregistration.Resource;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceDTO {
    private UUID resourceId;
    private String resourceName;
    private String description;
}
