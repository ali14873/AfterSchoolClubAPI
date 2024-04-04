package co.uk.afterschoolclub.userregistration.Roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "roles")
public class RoleTable implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String type;

    public RoleTable(String type){
        this.type = type;
        this.setAuthority(this.type);
    }


    public RoleTable(){}

    @Override
    public String getAuthority() {
        return this.getType();
    }


    public void setAuthority(String authority) {
        this.setType(authority);
    }
}
