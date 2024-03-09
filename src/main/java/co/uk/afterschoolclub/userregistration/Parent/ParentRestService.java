package co.uk.afterschoolclub.userregistration.Parent;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/parent")
public class ParentRestService {

    final ParentApplicationService parentApplicationService;

    @Autowired
    public ParentRestService(ParentApplicationService parentApplicationService) {
        this.parentApplicationService = parentApplicationService;
    }

    @PostMapping("/create")
    public ResponseEntity<ParentDTO> createParent(@RequestBody final ParentDTO request) {
        ParentDTO parent = parentApplicationService.createParent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(parent);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ParentDTO>> getAllParents() {
        List<ParentDTO> parentDTOList = parentApplicationService.getAllParents();
        return ResponseEntity.status(HttpStatus.OK).body(parentDTOList);
    }

    @GetMapping("/getParent/{id}")
    public ResponseEntity<ParentDTO> getParentById(@PathVariable UUID id) {
        try {
            ParentDTO parentDTO = parentApplicationService.getParentById(id);
            return ResponseEntity.status(HttpStatus.OK).body(parentDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteParentById(@PathVariable UUID id) {
        try {
            parentApplicationService.deleteParentById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/uploadCSV")
    public ResponseEntity<List<ParentDTO>> uploadParentsCSV(@RequestParam("File") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            List<ParentDTO> createdParents = parentApplicationService.processCSVFile(file.getInputStream());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdParents);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
