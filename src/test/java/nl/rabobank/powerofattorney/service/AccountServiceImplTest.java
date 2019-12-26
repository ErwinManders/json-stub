package nl.rabobank.powerofattorney.service;

import nl.rabobank.powerofattorney.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountServiceImplTest {

    private AccountService service;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setup() {
        service = new AccountServiceImpl(restTemplate);
    }

    @Test
    void testGetAccount() {
        // given
        final String accountId = "NL23RABO123456789";
        final Account account = new Account();
        account.setId(accountId);
        account.setBalance(50);
        account.setOwner("Levi");
        account.setCreated("12-10-2007");
        account.setEnded("13-06-2009");
        when(restTemplate.getForObject("null123456789", Account.class)).thenReturn(account);

        // when
        final Account result = service.getAccount(accountId);

        // then
        assertNotNull(result);
        assertEquals(accountId, account.getId());
        assertEquals(50, account.getBalance());
        assertEquals("Levi", account.getOwner());
        assertEquals("12-10-2007", account.getCreated());
        assertEquals("13-06-2009", account.getEnded());
    }

}