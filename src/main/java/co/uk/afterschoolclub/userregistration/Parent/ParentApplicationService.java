package co.uk.afterschoolclub.userregistration.Parent;

import com.opencsv.CSVReader;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ParentApplicationService {

    @Autowired
    ParentRepoInterface parentRepoInterface;

    public ParentDTO createParent(ParentDTO request) {
        ParentTable parent = ParentTable.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
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
}
