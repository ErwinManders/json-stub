package nl.rabobank.powerofattorney.service;

import nl.rabobank.powerofattorney.model.Account;

/**
 * Account service.
 */
interface AccountService {

    /**
     * Get the account by account number.
     *
     * @param accountNumber Account number.
     * @return {@link Account} instance.
     */
    Account getAccount(String accountNumber);
}
