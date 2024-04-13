package co.uk.afterschoolclub.userregistration.StudentEmergencyInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentEmergencyInfoApplicationService {

    private final StudentEmergencyInfoRepository repository;

    @Autowired
    public StudentEmergencyInfoApplicationService(StudentEmergencyInfoRepository repository) {
        this.repository = repository;
    }

    public long getTotalEmergencyContactCount() {
        return repository.countTotalEmergencyContacts();
    }

    public long getStudentsWithEmergencyContactCount() {
        return repository.countStudentsWithEmergencyContacts();
    }

    public long getStudentsWithMultipleEmergencyContactsCount() {
        return repository.countStudentsWithMultipleEmergencyContacts();
    }

    public double getPercentageOfStudentsWithMultipleEmergencyContacts() {
        Long totalStudentsWithContacts = getStudentsWithEmergencyContactCount();
        Long studentsWithMultipleContacts = getStudentsWithMultipleEmergencyContactsCount();

        if (totalStudentsWithContacts == null|| totalStudentsWithContacts == 0 || studentsWithMultipleContacts == null) {
            // Handle the case where there are no records or the count is zero
            return 0.0;
        }

        return (double) studentsWithMultipleContacts / totalStudentsWithContacts * 100;
    }
}
