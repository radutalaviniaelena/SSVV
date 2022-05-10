package ssvv.example;

import org.junit.jupiter.api.Assertions;
import ssvv.example.repository.NotaXMLRepository;
import ssvv.example.repository.StudentXMLRepository;
import ssvv.example.repository.TemaXMLRepository;
import ssvv.example.service.Service;
import ssvv.example.validation.NotaValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;

public class IncrementalIntegrationTesting {
    StudentValidator studentValidator;
    StudentXMLRepository studentXMLRepository;

    TemaValidator temaValidator;
    TemaXMLRepository temaXMLRepository;

    NotaValidator notaValidator;
    NotaXMLRepository notaXMLRepository;

    Service service;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.studentValidator = new StudentValidator();
        this.studentXMLRepository = new StudentXMLRepository(studentValidator, "students.xml");

        this.temaValidator = new TemaValidator();
        this.temaXMLRepository = new TemaXMLRepository(temaValidator, "assignments.xml");

        this.notaValidator = new NotaValidator();
        this.notaXMLRepository = new NotaXMLRepository(notaValidator, "grades.xml");

        this.service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    // test case for addStudent
    @org.junit.jupiter.api.Test
    public void addStudentIncrementalTest() {
        Assertions.assertEquals(1, service.saveStudent("2", "Maria", 936));
        service.deleteStudent("2");
    }

    // integration test for addAssignment (addStudent + addAssignment)
    @org.junit.jupiter.api.Test
    public void addStudentAssignmentIncrementalTest() {
        Assertions.assertEquals(1, service.saveStudent("2", "Maria", 936));
        Assertions.assertEquals(0, service.saveTema("2", "descriere", 20, 1));
        service.deleteStudent("2");
    }

    // integration test for addGrade (addStudent + addAssignment + addGrade)
   /* @org.junit.jupiter.api.Test
    public void addStudentAssignmentGradeIncrementalTest() {
        System.out.println("3\n");
        Assertions.assertEquals(1, service.saveStudent("2", "Maria", 936));
        Assertions.assertEquals(1, service.saveTema("2", "descriere", 3, 1));

        Assertions.assertEquals(1, service.saveNota("2", "2", 10, 3, "good"));

        service.deleteStudent("2");
        service.deleteTema("2");
    }*/

    // integration test for addGrade (addStudent + addAssignment + addGrade)
    @org.junit.jupiter.api.Test
    public void addStudentAssignmentGradeIncrementalTest_InputNotValid() {
        Assertions.assertEquals(1, service.saveStudent("2", "Maria", 936));
        Assertions.assertEquals(0, service.saveTema("2", "descriere", 50, 1));

        Assertions.assertEquals(
                -1, service.saveNota("2", "2", 10, 3, "good"));

        service.deleteStudent("2");
    }
}
