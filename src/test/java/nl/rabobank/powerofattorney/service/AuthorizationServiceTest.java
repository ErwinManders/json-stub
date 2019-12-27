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
    @Mock
    private DebitCardService debitCardService;
    @Mock
    private CreditCardService creditCardService;

    @BeforeEach
    void setup() {
        service = new AuthorizationServiceImpl(powerOfAttorneyService, accountService, debitCardService, creditCardService);
    }

    @Test
    void testGetAuthorizations() {
        // given
        final String acc1Id = "NL23RABO123456789";
        final String acc1Owner = "Owner One";
        final long acc1Balance = 150L;
        final String acc1Created = "12-10-2007";
        final Account acc1 = new Account(acc1Id, acc1Owner, acc1Balance, acc1Created);
        when(accountService.getAccount(acc1Id)).thenReturn(acc1);

        final String acc2Id = "NL23RABO333333333";
        final String acc2Owner = "Owner Three";
        final long acc2Balance = 450L;
        final String acc2Created = "24-05-2009";
        final Account acc2 = new Account(acc2Id, acc2Owner, acc2Balance, acc2Created);
        when(accountService.getAccount(acc2Id)).thenReturn(acc2);

        final String userGrantor = "testGrantor";
        final String userId = "testUser";
        final String direction = "GIVEN";

        final String poa1Id = "01";
        final String[] poa1Auths = {"DEBIT_CARD", "VIEW", "PAYMENT"};
        final String poa3Id = "03";
        final String[] poa3Auths = {"DEBIT_CARD", "VIEW", "PAYMENT"};

        final PowerOfAttorney poa1 = new PowerOfAttorney(poa1Id, userGrantor, userId, acc1Id, direction, poa1Auths, null);
        final PowerOfAttorney poa2 = new PowerOfAttorney("02", userGrantor, "anotherUser", "NL23RABO222222222", direction, poa1Auths, null);
        final PowerOfAttorney poa3 = new PowerOfAttorney(poa3Id, userGrantor, userId, acc2Id, direction, poa3Auths, null);
        final List<PowerOfAttorney> poas = Arrays.asList(poa1, poa2, poa3);
        when(powerOfAttorneyService.getPowerOfAttorneys()).thenReturn(poas);

        // when
        final AuthorizationInformation authorizationInformation = service.getAuthorizations(userId);

        //then
        assertNotNull(authorizationInformation);
        assertEquals(userId, authorizationInformation.getUserId());
        assertNotNull(authorizationInformation.getAggregatedInformations());
        assertEquals(2, authorizationInformation.getAggregatedInformations().size());
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

        final AggregatedInformation aggregatedInfo2 = authorizationInformation.getAggregatedInformations().get(1);
        assertEquals(poa3Id, aggregatedInfo2.getId());
        assertEquals(userGrantor, aggregatedInfo2.getGrantor());
        assertEquals(direction, aggregatedInfo2.getDirection());
        final Account account2 = aggregatedInfo2.getAccount();
        assertNotNull(account2);
        assertEquals(acc2Id, account2.getId());
        assertEquals(acc2Owner, account2.getOwner());
        assertEquals(acc2Balance, account2.getBalance());
        assertEquals(acc2Created, account2.getCreated());
        assertNull(account2.getEnded());
    }

}
