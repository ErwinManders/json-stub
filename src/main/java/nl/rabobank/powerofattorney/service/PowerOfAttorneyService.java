package nl.rabobank.powerofattorney.service;

import nl.rabobank.powerofattorney.model.PowerOfAttorney;

import java.util.List;

/**
 * Power of attorney service.
 */
interface PowerOfAttorneyService {

    /**
     * Get the list of {@link PowerOfAttorney}.
     *
     * @return List of {@link PowerOfAttorney}.
     */
    List<PowerOfAttorney> getPowerOfAttorneys();
}
