package nl.rabobank.powerofattorney.controller;

import nl.rabobank.powerofattorney.model.AutorizationInformation;
import nl.rabobank.powerofattorney.service.PowerOfAttorneyService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

/**
 * Power of attorney controller.
 */
@RestController
public class PowerOfAttorneyController {

    private final PowerOfAttorneyService service;

    /**
     * Constructor.
     *
     * @param service {@link PowerOfAttorneyService} instance.
     */
    public PowerOfAttorneyController(final PowerOfAttorneyService service) {
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
    public Response getAggregatedPOA(@PathVariable("userId") String userId) {
        final AutorizationInformation autorizationInformation = service.getAutorizations(userId);
        return Response.ok().entity(autorizationInformation).build();
    }

}
