package nl.rabobank.powerofattorney.controller;

import nl.rabobank.powerofattorney.model.AuthorizationInformation;
import nl.rabobank.powerofattorney.service.AuthorizationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

/**
 * Power of attorney controller.
 */
@RestController
public class PowerOfAttorneyController {

    private final AuthorizationService service;

    /**
     * Constructor.
     *
     * @param service {@link AuthorizationService} instance.
     */
    public PowerOfAttorneyController(final AuthorizationService service) {
        this.service = service;
    }

    /**
     * Get the aggregated information of the authorizations of an user by its userId.
     * Inactive products or accounts are taking into account.
     *
     * @param userId user id.
     * @return Aggregated information of the authorizations of an user.
     */
    @RequestMapping("/powerofattorney/authorizations/{userId}")
    public Response getAuthorizations(@PathVariable("userId") String userId) {
        final AuthorizationInformation authorizationInformation = service.getAuthorizations(userId);
        return Response.ok().entity(authorizationInformation).build();
    }

}
