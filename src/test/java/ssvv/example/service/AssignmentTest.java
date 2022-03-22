package ssvv.example.service;

import org.junit.Test;
import ssvv.example.repository.TemaXMLRepository;
import ssvv.example.validation.TemaValidator;

import static org.junit.Assert.assertEquals;

public class AssignmentTest {
    private final TemaValidator temaValidator = new TemaValidator();
    private final TemaXMLRepository temaXMLRepository =
            new TemaXMLRepository(temaValidator, "assignments.xml");
    private final Service service = new Service(null, temaXMLRepository, null);

    @Test
    public void addAssignment_ValidInput_AssignmentAddedSuccessfully() {
        assertEquals(1, service.saveTema("1", "aaa", 7, 5));
        service.deleteTema("1");
    }

    @Test
    public void addAssignment_AlreadyExistingAssignment_AssignmentNotAdded() {
        service.saveTema("1", "aaa", 7, 5);
        assertEquals(0, service.saveTema("1", "aaa", 7, 5));
        service.deleteTema("1");
    }

    @Test
    public void addAssignment_InvalidDeadlineInput_AssignmentNotAdded() {
        assertEquals(0, service.saveTema("1", "aaa", 20, 5));
    }
}
