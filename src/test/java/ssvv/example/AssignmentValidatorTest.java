package ssvv.example;

import org.junit.jupiter.api.Assertions;
import ssvv.example.domain.Tema;
import ssvv.example.validation.TemaValidator;
import ssvv.example.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

public class AssignmentValidatorTest {
    private final TemaValidator temaValidator = new TemaValidator();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    public void addAssignment_InvalidId_NullId_ThrowsError() {
        Tema tema = new Tema(null, "description", 3, 1);

        Exception exception = assertThrows(ValidationException.class, () -> {
            temaValidator.validate(tema);
        });

        String expectedMessage = "ID invalid! \n";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @org.junit.jupiter.api.Test
    public void addAssignment_InvalidId_EmptyId_ThrowsError() {
        Tema tema = new Tema("", "description", 3, 1);

        Exception exception = assertThrows(ValidationException.class, () -> {
            temaValidator.validate(tema);
        });

        String expectedMessage = "ID invalid! \n";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @org.junit.jupiter.api.Test
    public void addAssignment_InvalidDescription_NullDescription_ThrowsError() {
        Tema tema = new Tema("1", null, 3, 1);

        Exception exception = assertThrows(ValidationException.class, () -> {
            temaValidator.validate(tema);
        });

        String expectedMessage = "Descriere invalida! \n";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @org.junit.jupiter.api.Test
    public void addAssignment_InvalidDescription_EmptyDescription_ThrowsError() {
        Tema tema = new Tema("1", "", 3, 1);

        Exception exception = assertThrows(ValidationException.class, () -> {
            temaValidator.validate(tema);
        });

        String expectedMessage = "Descriere invalida! \n";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @org.junit.jupiter.api.Test
    public void addAssignment_InvalidDeadline_DeadlineTooLow_ThrowsError() {
        Tema tema = new Tema("1", "description", 0, 1);

        Exception exception = assertThrows(ValidationException.class, () -> {
            temaValidator.validate(tema);
        });

        String expectedMessage = "Deadline invalid! \n";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @org.junit.jupiter.api.Test
    public void addAssignment_InvalidDeadline_DeadlineTooHigh_ThrowsError() {
        Tema tema = new Tema("1", "description", 15, 1);

        Exception exception = assertThrows(ValidationException.class, () -> {
            temaValidator.validate(tema);
        });

        String expectedMessage = "Deadline invalid! \n";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @org.junit.jupiter.api.Test
    public void addAssignment_InvalidDeadline_DeadlineLowerThanStartline_ThrowsError() {
        Tema tema = new Tema("1", "description", 1, 3);

        Exception exception = assertThrows(ValidationException.class, () -> {
            temaValidator.validate(tema);
        });

        String expectedMessage = "Deadline invalid! \n";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @org.junit.jupiter.api.Test
    public void addAssignment_InvalidStartline_StartlineTooLow_ThrowsError() {
        Tema tema = new Tema("1", "description", 1, 0);

        Exception exception = assertThrows(ValidationException.class, () -> {
            temaValidator.validate(tema);
        });

        String expectedMessage = "Data de primire invalida! \n";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    /*@Test
    public void addAssignment_InvalidStartline_StartlineTooHigh_ThrowsError() {
        Tema tema = new Tema("1", "description", 1, 15);

        Exception exception = assertThrows(ValidationException.class, () -> {
            temaValidator.validate(tema);
        });

        String expectedMessage = "Data de primire invalida! \n";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void addAssignment_InvalidStartline_StartlineHigherThanDeadline_ThrowsError() {
        Tema tema = new Tema("1", "description", 1, 3);

        Exception exception = assertThrows(ValidationException.class, () -> {
            temaValidator.validate(tema);
        });

        String expectedMessage = "Data de primire invalida! \n";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }*/

    @org.junit.jupiter.api.Test
    public void addAssignment_ValidInput() {
        Tema tema = new Tema("1", "description", 3, 1);
        assertDoesNotThrow(() -> temaValidator.validate(tema));

    }
}
