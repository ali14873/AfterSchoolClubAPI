package co.uk.afterschoolclub.userregistration.Parent;

import co.uk.afterschoolclub.userregistration.Roles.RoleRepoInterface;
import co.uk.afterschoolclub.userregistration.Teacher.TeacherTable;
import com.opencsv.CSVReader;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParentApplicationService implements UserDetailsService {

    @Autowired
    ParentRepoInterface parentRepoInterface;

    @Autowired
    RoleRepoInterface roleRepoInterface;

    @Autowired
    PasswordEncoder encoder;

    public ParentDTO createParent(ParentDTO request) {
        ParentTable parent = ParentTable.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(encoder.encode(request.getPassword()))
                .role(roleRepoInterface.findRoleByType(request.getRoleType())).build();
        parentRepoInterface.save(parent);

        return request;
    }

    public List<ParentDTO> getAllParents() {
        List<ParentDTO> parents = new ArrayList<>();
        for(ParentTable parent : parentRepoInterface.findAll()) {
            ParentDTO dto = ParentDTO.builder()
                    .id(parent.getId())
                    .firstName(parent.getFirstName())
                    .lastName(parent.getLastName())
                    .email(parent.getEmail())
                    .phone(parent.getPhone())
                    .build();
            parents.add(dto);
        }
        return parents;
    }

    public ParentDTO getParentById(UUID id) {
        Optional<ParentTable> parentOptional = parentRepoInterface.findById(id);

        if (parentOptional.isPresent()) {
            ParentTable parent = parentOptional.get();
            return ParentDTO.builder()
                    .id(parent.getId())
                    .firstName(parent.getFirstName())
                    .lastName(parent.getLastName())
                    .email(parent.getEmail())
                    .phone(parent.getPhone())
                    .build();
        } else {
            throw new EntityNotFoundException("Parent not found with ID: " + id);
        }
    }

    public void deleteParentById(UUID id) {
        Optional<ParentTable> parentOptional = parentRepoInterface.findById(id);
        if (parentOptional.isPresent()) {
            parentRepoInterface.delete(parentOptional.get());
        } else {
            throw new EntityNotFoundException("Parent not found with ID: " + id);
        }
    }

    public List<ParentDTO> processCSVFile(InputStream inputStream) {
        List<ParentDTO> createdParents = new ArrayList<>();
        try (Reader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            CSVReader csvReader = new CSVReader(reader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                ParentDTO parentDTO = ParentDTO.builder()
                        .firstName(nextRecord[0])
                        .lastName(nextRecord[1])
                        .email(nextRecord[2])
                        .phone(nextRecord[3])
                        .build();
                createdParents.add(createParent(parentDTO));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error processing CSV file", e);
        }
        return createdParents;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ParentTable> parentOptional = parentRepoInterface.findByEmail(username);
        if (parentOptional.isPresent()) {
            return parentOptional.get();
        } else {
            throw new EntityNotFoundException("parent not found with username: " + username);
        }
    }
}
