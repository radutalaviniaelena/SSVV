package ssvv.example.service;

import org.junit.jupiter.api.Assertions;
import ssvv.example.domain.Student;
import ssvv.example.repository.StudentXMLRepository;
import ssvv.example.validation.StudentValidator;

public class ServiceTest {
    private final StudentValidator studentValidator = new StudentValidator();
    private final StudentXMLRepository studentXMLRepository =
            new StudentXMLRepository(studentValidator, "students.xml");
    private final Service service = new Service(studentXMLRepository, null, null);

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    // 1, 6, 8 - the id, name and group are valid
    @org.junit.jupiter.api.Test
    public void addStudent_ValidInput_StudentAddedCorrectly() {
        Student student1 = new Student("1", "Maria", 111);
        Student student2 = new Student("4", "Ana", 937);
        Assertions.assertEquals(1, service.saveStudent("1", "Maria", 111));
        Assertions.assertEquals(1, service.saveStudent("4", "Ana", 937));

        boolean found1 = false, found2 = false;

        Iterable<Student> students = service.findAllStudents();
        for(Student s: students) {
            if(s.getID().equals(student1.getID())) {
                Assertions.assertEquals(student1.getGrupa(), s.getGrupa());
                Assertions.assertEquals(student1.getNume(), s.getNume());

                found1 = true;
            }

            if(s.getID().equals(student2.getID())) {
                Assertions.assertEquals(student2.getGrupa(), s.getGrupa());
                Assertions.assertEquals(student2.getNume(), s.getNume());

                found2 = true;
            }
        }

        Assertions.assertTrue(found1);
        Assertions.assertTrue(found2);
        service.deleteStudent("1");
        service.deleteStudent("4");
    }

    // 2 - the group is invalid (lower than 111)
    @org.junit.jupiter.api.Test
    public void addStudent_InvalidInput_GroupLower_StudentNotAdded() {
        Assertions.assertEquals(0, service.saveStudent("1", "Maria", 110));
    }

    // 3 - the group is invalid (higher than 937)
    @org.junit.jupiter.api.Test
    public void addStudent_InvalidInput_GroupHigher_StudentNotAdded() {
        Assertions.assertEquals(0, service.saveStudent("1", "Maria", 938));
    }

    // 7 - the name is invalid (empty string)
    @org.junit.jupiter.api.Test
    public void addStudent_InvalidInput_EmptyStringName_StudentNotAdded() {
        Assertions.assertEquals(0, service.saveStudent("1", "", 123));
    }

    // 7 - the name is invalid (null)
    @org.junit.jupiter.api.Test
    public void addStudent_InvalidInput_NullName_StudentNotAdded() {
        Assertions.assertEquals(0, service.saveStudent("1", null, 123));
    }

    // 9 - the id is invalid (empty string)
    @org.junit.jupiter.api.Test
    public void addStudent_InvalidInput_EmptyStringId_StudentNotAdded() {
        Assertions.assertEquals(0, service.saveStudent("", "Ana", 123));
    }

    // 9 - the id is invalid (null)
    @org.junit.jupiter.api.Test
    public void addStudent_InvalidInput_NullId_StudentNotAdded() {
        Assertions.assertEquals(0, service.saveStudent(null, "Ana", 123));
    }

    // 10 - the same id for two different students
    @org.junit.jupiter.api.Test
    public void addStudentService_InvalidInput_AlreadyExistingID_CorrectLength() {
        service.saveStudent("3", "Maria", 936);

        Assertions.assertEquals(0, service.saveStudent("3", "Ioana", 123));

        int length = 0;
        for(Student ignored : service.findAllStudents()) {
            length++;
        }

        Assertions.assertEquals(1, length);
        service.deleteStudent("3");
    }

    @org.junit.jupiter.api.Test
    public void ValidInput_CorrectElement_AddStudent() {
        Student student = new Student("2", "Maria", 936);
        service.saveStudent("2", "Maria", 936);

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
        service.deleteStudent("2");
    }
}