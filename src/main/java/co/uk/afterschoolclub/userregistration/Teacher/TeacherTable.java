package co.uk.afterschoolclub.userregistration.Teacher;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TeacherTable")
public class TeacherTable implements UserDetails {

    @Id
    @Column(name = "TeacherID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Size(max = 50)
    @Column(name = "FirstName", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "LastName", length = 50)
    private String lastName;

    @Size(max = 50)
    @Column(name = "Email", length = 50)
    private String email;

    @Size(max = 50)
    @Column(name = "Phone", length = 50)
    private String phone;

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return null;
    }

    /**
     * Returns the username used to authenticate the user. Cannot return
     * <code>null</code>.
     *
     * @return the username (never <code>null</code>)
     */
    @Override
    public String getUsername() {
        return null;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    public boolean isEnabled() {
        return false;
    }
}