package test;

import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.TemaXMLRepository;
import service.Service;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddAssignementTest {

    private Service service;

    @BeforeEach
    void setUp() {
        Validator<Tema> temaValidator = new TemaValidator();
        TemaXMLRepository temaXmlRepo = new TemaXMLRepository(temaValidator, "teme.xml");
        service = new Service(null, temaXmlRepo, null);
    }

    @Test
    void testSaveTema_ValidTema() {
        int result = service.saveTema("T1", "Description", 10, 5);
        assertEquals(0, result, "Saving a valid tema should return 1 (success).");
    }

    @Test
    void testSaveTema_EmptyId() {
        int result = service.saveTema("", "Description", 10, 5);
        assertEquals(1, result, "Saving a tema with an empty ID should return 0 (failure).");
    }
}