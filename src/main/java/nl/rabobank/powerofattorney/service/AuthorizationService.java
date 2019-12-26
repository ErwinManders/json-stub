package nl.rabobank.powerofattorney.service;

import nl.rabobank.powerofattorney.model.AuthorizationInformation;

/**
 * Authorization service.
 */
public interface AuthorizationService {

    /**
     * Get the aggregated information of the authorizations of an user by its userId,
     * Inactive products or accounts are taking into account.
     *
     * @param userId user id.
     * @return Aggregated information of the authorizations of an user.
     */
    AuthorizationInformation getAuthorizations(String userId);

}
