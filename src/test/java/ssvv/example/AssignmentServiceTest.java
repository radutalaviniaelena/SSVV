package ssvv.example;

import org.junit.jupiter.api.Assertions;
import ssvv.example.repository.TemaXMLRepository;
import ssvv.example.service.Service;
import ssvv.example.validation.TemaValidator;

public class AssignmentServiceTest {
    private final TemaValidator temaValidator = new TemaValidator();
    private final TemaXMLRepository temaXMLRepository =
            new TemaXMLRepository(temaValidator, "assignments.xml");
    private final Service service = new Service(null, temaXMLRepository, null);

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    public void addAssignment_ValidInput_AssignmentAddedSuccessfully() {
        Assertions.assertEquals(1, service.saveTema("1", "descriere", 3, 1));
        service.deleteTema("1");
    }

    @org.junit.jupiter.api.Test
    public void addAssignment_InvalidDeadlineInput_AssignmentNotAdded() {
        Assertions.assertEquals(0, service.saveTema("1", "descriere", 20, 1));
    }

    @org.junit.jupiter.api.Test
    public void addAssignment_AlreadyExistingAssignment_AssignmentNotAdded() {
        service.saveTema("1", "descriere1", 7, 5);
        Assertions.assertEquals(0, service.saveTema("1", "descriere1", 3, 1));
        service.deleteTema("1");
    }
}