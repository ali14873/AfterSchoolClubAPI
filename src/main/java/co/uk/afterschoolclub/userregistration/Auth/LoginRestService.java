package co.uk.afterschoolclub.userregistration.Auth;

import co.uk.afterschoolclub.userregistration.Teacher.TeacherDTO;
import co.uk.afterschoolclub.userregistration.Teacher.TeacherTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginRestService {

    @Autowired
    private AuthApplicationService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()){
            //Perform the token generation procedure and return the token as a response
            LoginResponse response = this.authService.login(authentication);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("Error");
    }

}
