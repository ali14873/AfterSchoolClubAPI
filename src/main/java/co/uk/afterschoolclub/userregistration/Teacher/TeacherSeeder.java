package co.uk.afterschoolclub.userregistration.Teacher;

import co.uk.afterschoolclub.userregistration.Constants.RoleConstants;
import co.uk.afterschoolclub.userregistration.Roles.RoleTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TeacherSeeder implements CommandLineRunner {
    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TeacherRepoInterface teacherRepoInterface;

    @Override
    public void run(String... args) throws Exception {
        if (teacherRepoInterface.findByEmail("ali@ali.co.uk").isEmpty()) {
            TeacherTable teacher =
                    TeacherTable.builder()
                            .firstName("Ali")
                            .lastName("abdalrahman")
                            .email("ali@ali.co.uk")
                            .password(encoder.encode("123123"))
                            .role(new RoleTable(RoleConstants.ADMIN))
                            .build();
            teacherRepoInterface.save(teacher);
        }
    }
}
