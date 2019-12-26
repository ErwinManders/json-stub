package nl.rabobank.powerofattorney.service;

import nl.rabobank.powerofattorney.model.Account;
import nl.rabobank.powerofattorney.model.AggregatedInformation;
import nl.rabobank.powerofattorney.model.AuthorizationInformation;
import nl.rabobank.powerofattorney.model.PowerOfAttorney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthorizationServiceTest {

    private AuthorizationService service;

    @Mock
    private PowerOfAttorneyService powerOfAttorneyService;
    @Mock
    private AccountService accountService;

    @BeforeEach
    void setup() {
        service = new AuthorizationServiceImpl(powerOfAttorneyService, accountService);
    }

    @Test
    void testGetAuthorizations() {
        // given
        final String acc1Id = "NL23RABO123456789";
        final String acc1Owner = "Owner One";
        final long acc1Balance = 150L;
        final String acc1Created = "12-10-2007";
        final Account acc1 = new Account(acc1Id, acc1Owner, acc1Balance, acc1Created, null);
        when(accountService.getAccount(acc1Id)).thenReturn(acc1);

        final String userGrantor = "testGrantor";
        final String userId = "testUser";
        final String direction = "GIVEN";

        final String poa1Id = "01";
        final String[] poa1Auths = {"DEBIT_CARD", "VIEW", "PAYMENT"};

        final PowerOfAttorney poa1 = new PowerOfAttorney(poa1Id, userGrantor, userId, acc1Id, direction, poa1Auths, null);
        final List<PowerOfAttorney> poas = Arrays.asList(poa1);
        when(powerOfAttorneyService.getPowerOfAttorneys()).thenReturn(poas);

        // when
        final AuthorizationInformation authorizationInformation = service.getAuthorizations(userId);

        //then
        assertNotNull(authorizationInformation);
        assertEquals(userId, authorizationInformation.getUserId());
        assertNotNull(authorizationInformation.getAggregatedInformations());
        assertEquals(1, authorizationInformation.getAggregatedInformations().size());
        final AggregatedInformation aggregatedInfo1 = authorizationInformation.getAggregatedInformations().get(0);
        assertEquals(poa1Id, aggregatedInfo1.getId());
        assertEquals(userGrantor, aggregatedInfo1.getGrantor());
        assertEquals(direction, aggregatedInfo1.getDirection());
        final Account account1 = aggregatedInfo1.getAccount();
        assertNotNull(account1);
        assertEquals(acc1Id, account1.getId());
        assertEquals(acc1Owner, account1.getOwner());
        assertEquals(acc1Balance, account1.getBalance());
        assertEquals(acc1Created, account1.getCreated());
        assertNull(account1.getEnded());
    }

}
