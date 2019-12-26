package nl.rabobank.powerofattorney.service;

import nl.rabobank.powerofattorney.model.AuthorizationInformation;
import nl.rabobank.powerofattorney.model.PowerOfAttorney;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PowerOfAttorneyServiceImplTest {

    @InjectMocks
    private PowerOfAttorneyServiceImpl service;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void testGetAuthorizations() {
        // given
        final String userId = "testUser";
        final PowerOfAttorney poa1 = new PowerOfAttorney();
        poa1.setId("1");
        poa1.setGrantee(userId);
        final PowerOfAttorney[] poas = {poa1};
        when(restTemplate.getForObject("null", PowerOfAttorney[].class)).thenReturn(poas);
        when(restTemplate.getForObject("null1", PowerOfAttorney.class)).thenReturn(poa1);

        // when
        final AuthorizationInformation authorizationInformation = service.getAuthorizations(userId);

        //then
        assertNotNull(authorizationInformation);
        assertEquals(userId, authorizationInformation.getUserId());
        assertNotNull(authorizationInformation.getAggregatedInformations());
        assertEquals(0, authorizationInformation.getAggregatedInformations().size());
    }

}