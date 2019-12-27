package nl.rabobank.powerofattorney.service;

import nl.rabobank.powerofattorney.model.DebitCard;

/**
 * Debit card service.
 */
interface DebitCardService {

    /**
     * Get the debit card by id.
     *
     * @param id Debit card id.
     * @return {@link DebitCard} instance.
     */
    DebitCard getDebitCard(String id);
}
