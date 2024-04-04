package co.uk.afterschoolclub.userregistration.Roles;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepoInterface extends CrudRepository<RoleTable, Long> {
    RoleTable findRoleByType(String type);

    RoleTable findRoleById(Long id);
}
