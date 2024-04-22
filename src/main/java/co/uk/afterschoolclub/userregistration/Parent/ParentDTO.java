package co.uk.afterschoolclub.userregistration.Parent;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ParentDTO {


    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String roleType;
}
