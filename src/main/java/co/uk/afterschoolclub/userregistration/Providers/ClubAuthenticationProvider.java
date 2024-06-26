package co.uk.afterschoolclub.userregistration.Providers;

import co.uk.afterschoolclub.userregistration.Auth.IUserDetails;
import co.uk.afterschoolclub.userregistration.Constants.RoleConstants;
import co.uk.afterschoolclub.userregistration.Parent.ParentApplicationService;
import co.uk.afterschoolclub.userregistration.Teacher.TeacherApplicationService;
import co.uk.afterschoolclub.userregistration.Teacher.TeacherTable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class ClubAuthenticationProvider implements AuthenticationProvider {
    /**
     * Performs authentication with the same contract as
     * {@link AuthenticationManager#authenticate(Authentication)}
     * .
     *
     * @param authentication the authentication request object.
     * @return a fully authenticated object including credentials. May return
     * <code>null</code> if the <code>AuthenticationProvider</code> is unable to support
     * authentication of the passed <code>Authentication</code> object. In such a case,
     * the next <code>AuthenticationProvider</code> that supports the presented
     * <code>Authentication</code> class will be tried.
     * @throws AuthenticationException if authentication fails.
     */

    private PasswordEncoder passwordEncoder;


    private TeacherApplicationService teacherApplicationService;

    @Autowired
    private ParentApplicationService parentApplicationService; // Assume you have this service






    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!this.supports(authentication.getClass())) {
            return null;
        }
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        String role = getRole(username);  // Function to determine the role based on username

        IUserDetails userDetails = null;
        switch (role) {
            case RoleConstants.ADMIN:
                // Admin authentication logic (if you have an AdminApplicationService)
                break;
            case RoleConstants.TEACHER:
                userDetails = (IUserDetails) teacherApplicationService.loadUserByUsername(username);
                break;
            case RoleConstants.PARENT:
                userDetails = (IUserDetails) parentApplicationService.loadUserByUsername(username);
                break;
        }

        if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
            token.setDetails(userDetails);
            return token;
        }
        return null;
    }



    protected String getRole(String username){
        if (username.contains("@admin")){
            return RoleConstants.ADMIN;
        }else if(username.contains("@staff")){
            return RoleConstants.TEACHER;
        }else{
            return RoleConstants.PARENT;
        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
