package ssvv.example;

import org.junit.jupiter.api.Assertions;
import ssvv.example.domain.Student;
import ssvv.example.repository.NotaXMLRepository;
import ssvv.example.repository.StudentXMLRepository;
import ssvv.example.repository.TemaXMLRepository;
import ssvv.example.service.Service;
import ssvv.example.validation.NotaValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;

public class BingBangIntegrationTesting {
    private final StudentValidator studentValidator = new StudentValidator();
    private final StudentXMLRepository studentXMLRepository =
            new StudentXMLRepository(studentValidator, "students.xml");

    private final TemaValidator temaValidator = new TemaValidator();
    private final TemaXMLRepository temaXMLRepository =
            new TemaXMLRepository(temaValidator, "assignments.xml");

    private final NotaValidator notaValidator = new NotaValidator();
    private final NotaXMLRepository notaXMLRepository =
            new NotaXMLRepository(notaValidator, "grades.xml");

    private final Service service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    // test case for addStudent
    @org.junit.jupiter.api.Test
    public void ValidInput_CorrectElement_AddStudent() {
        Student student = new Student("1", "Maria", 936);
        service.saveStudent("1", "Maria", 936);

        boolean found = false;

        Iterable<Student> students = service.findAllStudents();
        for(Student s: students) {
            if(s.getID().equals(student.getID())) {
                Assertions.assertEquals(student.getNume(), s.getNume());
                Assertions.assertEquals(student.getGrupa(), s.getGrupa());

                found = true;
            }
        }

        Assertions.assertTrue(found);
        service.deleteStudent("1");
    }

    // test case for addAssignment
    @org.junit.jupiter.api.Test
    public void addAssignment_ValidInput_AssignmentAddedSuccessfully() {
        Assertions.assertEquals(1, service.saveTema("1", "descriere", 3, 1));
        service.deleteTema("1");
    }

    // test case for addGrade
    @org.junit.jupiter.api.Test
    public void addGrade_ValidInput_GradeAddedSuccessfully() {
        Assertions.assertEquals(-1,
                service.saveNota("100", "100", 1000, 3, "good"));
    }

    @org.junit.jupiter.api.Test
    public void bing_bang_integration_test() {
        ValidInput_CorrectElement_AddStudent();
        addAssignment_ValidInput_AssignmentAddedSuccessfully();
        addGrade_ValidInput_GradeAddedSuccessfully();
    }
}
