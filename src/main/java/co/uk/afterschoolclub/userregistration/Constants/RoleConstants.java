package co.uk.afterschoolclub.userregistration.Constants;

import java.util.HashMap;
import java.util.Map;

public class RoleConstants {

    public static final String ADMIN = "Admin";
    public static final String PARENT = "Parent";
    public static final String TEACHER = "Teacher";

    public static Map<String, Long> getRoles(){
        Map<String, Long> roles = new HashMap<>();
        roles.put(ADMIN, 1L);
        roles.put(PARENT, 2L);
        roles.put(TEACHER,3L);
        return roles;
    }


}
