package co.uk.afterschoolclub.userregistration.Roles;

import co.uk.afterschoolclub.userregistration.Constants.RoleConstants;
import co.uk.afterschoolclub.userregistration.Roles.RoleRepoInterface;
import co.uk.afterschoolclub.userregistration.Roles.RoleTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;

public class RoleSeeder implements CommandLineRunner {
    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Autowired
    private RoleRepoInterface roleRepoInterface;
    @Override
    public void run(String... args) throws Exception {
        if (!(roleRepoInterface.count() > 0)){
            roleRepoInterface.saveAll(
                    Arrays.asList(new RoleTable(RoleConstants.ADMIN),
                            new RoleTable(RoleConstants.TEACHER),
                            new RoleTable(RoleConstants.PARENT))
            );
        }
    }
}
