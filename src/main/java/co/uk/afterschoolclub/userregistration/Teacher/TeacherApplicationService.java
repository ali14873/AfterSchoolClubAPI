package co.uk.afterschoolclub.userregistration.Teacher;

import co.uk.afterschoolclub.userregistration.Roles.RoleRepoInterface;
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
public class TeacherApplicationService implements UserDetailsService {

    @Autowired
    TeacherRepoInterface teacherRepoInterface;

    @Autowired
    RoleRepoInterface roleRepoInterface;

    @Autowired
    PasswordEncoder encoder;

    public TeacherDTO createTeacher(TeacherDTO request) {
        TeacherTable teacher = TeacherTable.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(encoder.encode(request.getPassword()))
                .role(roleRepoInterface.findRoleByType(request.getRoleType()))
                .build();
        teacherRepoInterface.save(teacher);

        return request;
    }

    public List<TeacherDTO> getAllTeachers() {
        List<TeacherDTO> teachers = new ArrayList<>();
        for(TeacherTable teacher : teacherRepoInterface.findAll()) {
            TeacherDTO dto = TeacherDTO.builder()
                    .id(teacher.getId())
                    .firstName(teacher.getFirstName())
                    .lastName(teacher.getLastName())
                    .email(teacher.getEmail())
                    .phone(teacher.getPhone())
                    .build();
            teachers.add(dto);
        }
        return teachers;
    }

    public TeacherDTO getTeacherById(UUID id) {
        Optional<TeacherTable> teacherOptional = teacherRepoInterface.findById(id);

        if (teacherOptional.isPresent()) {
            TeacherTable teacher = teacherOptional.get();
            return TeacherDTO.builder()
                    .id(teacher.getId())
                    .firstName(teacher.getFirstName())
                    .lastName(teacher.getLastName())
                    .email(teacher.getEmail())
                    .phone(teacher.getPhone())
                    .build();
        } else {
            throw new EntityNotFoundException("Teacher not found with ID: " + id);
        }
    }

    public void deleteTeacherById(UUID id) {
        Optional<TeacherTable> teacherOptional = teacherRepoInterface.findById(id);
        if (teacherOptional.isPresent()) {
            teacherRepoInterface.delete(teacherOptional.get());
        } else {
            throw new EntityNotFoundException("Teacher not found with ID: " + id);
        }
    }

    public List<TeacherDTO> processCSVFile(InputStream inputStream) {
        List<TeacherDTO> createdTeachers = new ArrayList<>();
        try (Reader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            CSVReader csvReader = new CSVReader(reader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                TeacherDTO teacherDTO = TeacherDTO.builder()
                        .firstName(nextRecord[0])
                        .lastName(nextRecord[1])
                        .email(nextRecord[2])
                        .phone(nextRecord[3])
                        .build();
                createdTeachers.add(createTeacher(teacherDTO));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error processing CSV file", e);
        }
        return createdTeachers;
    }

    // In TeacherApplicationService.java

    public TeacherDTO updateTeacher(UUID id, TeacherDTO request) {
        Optional<TeacherTable> teacherOptional = teacherRepoInterface.findById(id);
        if (teacherOptional.isEmpty()) {
            throw new EntityNotFoundException("Teacher not found with ID: " + id);
        }
        TeacherTable teacher = teacherOptional.get();
        // Update fields
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());
        teacher.setPhone(request.getPhone());
//        teacher.setPassword(encoder.encode(request.getPassword())); // Re-encode password
//        teacher.setRole(roleRepoInterface.findRoleByType(request.getRoleType()));

        teacherRepoInterface.save(teacher);

        return TeacherDTO.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .email(teacher.getEmail())
                .phone(teacher.getPhone())
//                .password(null) // It's a good practice not to return password data
//                .roleType(request.getRoleType())
                .build();
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
        Optional<TeacherTable> teacherOptional = teacherRepoInterface.findByEmail(username);
        if (teacherOptional.isPresent()) {
            return teacherOptional.get();
        } else {
            throw new EntityNotFoundException("Teacher not found with username: " + username);
        }
    }
}
