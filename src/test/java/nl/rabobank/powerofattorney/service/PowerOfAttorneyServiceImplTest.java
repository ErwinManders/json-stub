package nl.rabobank.powerofattorney.service;

import nl.rabobank.powerofattorney.model.AutorizationInformation;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PowerOfAttorneyServiceImplTest {

    @InjectMocks
    PowerOfAttorneyServiceImpl service;

    @Test
    void testGetAutorizations() {
        // given
        final String userId = "testUser";

        // when
        final AutorizationInformation autorizationInformation = service.getAutorizations(userId);

        //then
        assertNotNull(autorizationInformation);
        assertEquals(userId, autorizationInformation.getUserId());
    }

}