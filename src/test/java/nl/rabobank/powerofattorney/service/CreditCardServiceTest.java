package nl.rabobank.powerofattorney.service;

import nl.rabobank.powerofattorney.model.CreditCard;
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
class CreditCardServiceTest {

    private static final String SERVICE_URL = "http://rbservice.tst.nl:654/crcs/";

    private CreditCardService service;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        service = new CreditCardServiceImpl(restTemplate);
        ReflectionTestUtils.setField(service, "url", SERVICE_URL);
    }

    @Test
    void testGetCreditCard() {
        // given
        final String id = "3333";
        final String status = "ACTIVE";
        final long cardNumber = 5075;
        final int sequenceNumber = 1;
        final String cardHolder = "Bilbo Basggins";
        final long monthlyLimit = 3200L;

        final CreditCard creditCard = new CreditCard(id, status, cardNumber, sequenceNumber, cardHolder, monthlyLimit);
        when(restTemplate.getForObject(SERVICE_URL + id, CreditCard.class)).thenReturn(creditCard);

        // when
        final CreditCard result = service.getCreditCard(id);

        // then
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(status, result.getStatus());
        assertEquals(cardNumber, result.getCardNumber());
        assertEquals(sequenceNumber, result.getSequenceNumber());
        assertEquals(cardHolder, result.getCardHolder());
        assertEquals(monthlyLimit, result.getMonthlyLimit());
    }
}
