package nl.rabobank.powerofattorney.service;

import nl.rabobank.powerofattorney.model.PowerOfAttorney;
import nl.rabobank.powerofattorney.model.AutorizationInformation;

import java.util.List;

/**
 * Power of attorney service.
 */
public interface PowerOfAttorneyService {

    /**
     * Get the aggregated information of the authorizations of an user by its userId,
     * Inactive products or accounts are taking into account.
     *
     * @param userId user id.
     * @return Aggregated information of the authorizations of an user.
     */
    AutorizationInformation getAutorizations(String userId);

    /**
     * Get the list of power of attorneys
     * @return List of {@link PowerOfAttorney}.
     */
    List<PowerOfAttorney> getPowerOfAttorneys();
}
