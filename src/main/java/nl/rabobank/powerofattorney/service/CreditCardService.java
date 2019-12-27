package nl.rabobank.powerofattorney.service;

import nl.rabobank.powerofattorney.model.CreditCard;

/**
 * Credit card service.
 */
interface CreditCardService {

    /**
     * Get the credit card by id.
     *
     * @param id Credit card id.
     * @return {@link CreditCard} instance.
     */
    CreditCard getCreditCard(String id);
}
