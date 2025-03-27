package test;

import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.StudentXMLRepository;
import service.Service;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddStudentTest {

    private Service service;

    @BeforeEach
    void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        service = new Service(fileRepository1, null, null);
    }

    @Test
    void testSaveStudent_ValidStudent() {
        int result = service.saveStudent("S123", "John Doe", 100);
        assertEquals(1, result, "Saving a valid student should return 1 (success).");
    }

    @Test
    void testSaveStudent_EmptyId() {
        int result = service.saveStudent("", "John Doe", 100);
        assertEquals(1, result);
    }

    @Test
    void testSaveStudent_NullId() {
        int result = service.saveStudent(null, "John Doe", 100);
        assertEquals(1, result, "Saving a student with a null ID should return 0 (failure).");
    }

    @Test
    void testSaveStudent_InvalidIdSpecialChars() {
        int result = service.saveStudent("@#$%", "John Doe", 100);
        assertEquals(1, result, "Saving a student with special characters in ID should return 0 (failure).");
    }

    @Test
    void testSaveStudent_EmptyName() {
        int result = service.saveStudent("S123", "", 101);
        assertEquals(1, result, "Saving a student with an empty name should return 0 (failure).");
    }

    @Test
    void testSaveStudent_NullName() {
        int result = service.saveStudent("S123", null, 101);
        assertEquals(1, result, "Saving a student with a null name should return 0 (failure).");
    }

    @Test
    void testSaveStudent_NameWithNumbers() {
        int result = service.saveStudent("S123", "John123", 101);
        assertEquals(1, result, "Saving a student with numbers in the name should return 0 (failure).");
    }

    @Test
    void testSaveStudent_NegativeGroup() {
        int result = service.saveStudent("S123", "John Doe", -10);
        assertEquals(1, result, "Saving a student with a negative group should return 0 (failure).");
    }

    @Test
    void testSaveStudent_ZeroGroup() {
        int result = service.saveStudent("S123", "John Doe", 0);
        assertEquals(1, result, "Saving a student with a group of 0 should return 0 (failure).");
    }

    @Test
    void testSaveStudent_MinValidGroup() {
        int result = service.saveStudent("S123", "John Doe", 1);
        assertEquals(1, result, "Saving a student with the minimum valid group (1) should return 1 (success).");
    }

    @Test
    void testSaveStudent_PositiveGroup() {
        int result = service.saveStudent("S123", "John Doe", 9999);
        assertEquals(1, result, "Saving a student with a large positive group should return 1 (success).");
    }
}
