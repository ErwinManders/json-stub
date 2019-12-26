package nl.rabobank.powerofattorney.service;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.powerofattorney.model.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of {@link AccountService}.
 */
@Component
@Slf4j
class AccountServiceImpl implements AccountService {

    @Value("${accounts.url}")
    private String url;

    private final RestTemplate restTemplate;

    /**
     * Constructor.
     *
     * @param restTemplate {@link RestTemplate} instance.
     */
    AccountServiceImpl(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Account getAccount(final String accountNumber) {
        final String id = accountNumber.replace("NL23RABO", "");
        Account account = null;
        try {
            final String accountIdUrl = String.format("%s%s", url, id);
            account = restTemplate.getForObject(accountIdUrl, Account.class);
            if (account != null) {
                account.setId(accountNumber);
                log.info(account + " loaded.");
            }
        } catch (Exception e) {
            log.error("Error loading account: id=" + id, e);
        }
        return account;
    }

}
