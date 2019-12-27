package nl.rabobank.powerofattorney.service;

import nl.rabobank.powerofattorney.model.DebitCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class DebitCardServiceTest {

    private static final String SERVICE_URL = "http://rbservice.tst.nl:654/dbcs/";

    private DebitCardService service;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        service = new DebitCardServiceImpl(restTemplate);
        ReflectionTestUtils.setField(service, "url", SERVICE_URL);
    }

    @Test
    void testGetDebitCard() {
        // given
        final String id = "1111";
        final DebitCard debitCard = new DebitCard(id, null, null);
        when(restTemplate.getForObject(SERVICE_URL + id, DebitCard.class)).thenReturn(debitCard);

        // when
        final DebitCard result = service.getDebitCard(id);

        // then
        assertNotNull(result);
        assertEquals(id, result.getId());
    }
}
