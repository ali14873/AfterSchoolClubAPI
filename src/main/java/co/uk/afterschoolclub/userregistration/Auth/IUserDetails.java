package co.uk.afterschoolclub.userregistration.Auth;

import co.uk.afterschoolclub.userregistration.Roles.RoleTable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface IUserDetails extends UserDetails {
    UUID getId();

    RoleTable getRole();

}
