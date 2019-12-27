package nl.rabobank.powerofattorney.service;

import nl.rabobank.powerofattorney.model.PowerOfAttorney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class PowerOfAttorneyServiceTest {

    private static final String SERVICE_URL = "http://rbservice.tst.nl:654/poas/";

    private PowerOfAttorneyService service;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setup() {
        service = new PowerOfAttorneyServiceImpl(restTemplate);
        ReflectionTestUtils.setField(service, "url", SERVICE_URL);
    }

    @Test
    void testGetPowerOfAttorneys() {
        // given
        final String poa1Id = "01";
        final String poa2Id = "02";

        final PowerOfAttorney poa1 = new PowerOfAttorney();
        poa1.setId(poa1Id);
        final PowerOfAttorney poa2 = new PowerOfAttorney();
        poa2.setId(poa2Id);
        final PowerOfAttorney[] poas = {poa1, poa2};

        when(restTemplate.getForObject(SERVICE_URL, PowerOfAttorney[].class)).thenReturn(poas);
        when(restTemplate.getForObject(SERVICE_URL + poa1Id, PowerOfAttorney.class)).thenReturn(poa1);
        when(restTemplate.getForObject(SERVICE_URL + poa2Id, PowerOfAttorney.class)).thenReturn(poa2);

        // when
        final List<PowerOfAttorney> result = service.getPowerOfAttorneys();

        //then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(poa1Id, result.get(0).getId());
        assertEquals(poa2Id, result.get(1).getId());
    }

}
