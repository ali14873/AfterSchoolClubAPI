package co.uk.afterschoolclub.userregistration.StudentClubEnrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentClubEnrollmentApplicationService {

    private final StudentClubEnrollmentRepository repository;

    @Autowired
    public StudentClubEnrollmentApplicationService(StudentClubEnrollmentRepository repository) {
        this.repository = repository;
    }

    public Long countStudentsWithoutClubs() {
        // Call repository method to count students without clubs
        return repository.countStudentsWithoutClubs();
    }

    public Long countStudentsEnrolledInClubs() {
        // Call repository method to count students enrolled in clubs
        return repository.countStudentsEnrolledInClubs();
    }

    public Long countOfClubs() {
        // Call repository method to count the total number of clubs
        return repository.countClubs();
    }
}
