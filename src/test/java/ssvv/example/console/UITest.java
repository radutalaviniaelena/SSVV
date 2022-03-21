package ssvv.example.console;

import org.junit.Test;
import ssvv.example.repository.StudentXMLRepository;
import ssvv.example.service.Service;
import ssvv.example.validation.StudentValidator;

import static org.junit.Assert.assertEquals;

public class UITest {
    private final StudentValidator studentValidator = new StudentValidator();
    private final StudentXMLRepository studentXMLRepository =
            new StudentXMLRepository(studentValidator, "students.xml");
    private final Service service = new Service(studentXMLRepository, null, null);


    /*// 4 - the group is invalid (it is a string value)
    @Test
    public void addStudent_InvalidInput_GroupAsString_StudentNotAdded() {
        assertEquals(0, service.saveStudent("1", "Maria", "ana"));
    }

    // 5 - the group is invalid (it is a string value)
    @Test
    public void addStudent_InvalidInput_GroupAsRealValue_StudentNotAdded() {
        assertEquals(0, service.saveStudent("1", "Maria", 1.2));
    }*/
}
