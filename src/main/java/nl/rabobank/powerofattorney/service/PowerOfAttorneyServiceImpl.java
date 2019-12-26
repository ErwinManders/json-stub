package nl.rabobank.powerofattorney.service;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.powerofattorney.model.AggregatedInformation;
import nl.rabobank.powerofattorney.model.PowerOfAttorney;
import nl.rabobank.powerofattorney.model.AuthorizationInformation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of {@link PowerOfAttorneyService}.
 */
@Component
@Slf4j
public class PowerOfAttorneyServiceImpl implements PowerOfAttorneyService {

    @Value("${powerofattorneys.url}")
    private String url;

    private final RestTemplate restTemplate;

    /**
     * Constructor.
     *
     * @param restTemplate {@link RestTemplate} instance.
     */
    public PowerOfAttorneyServiceImpl(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AuthorizationInformation getAuthorizations(String userId) {
        final AuthorizationInformation authorizationInformation = new AuthorizationInformation();
        authorizationInformation.setUserId(userId);

        final List<PowerOfAttorney> powerOfAttorneys = getPowerOfAttorneys();
        final List<AggregatedInformation> aggregatedInformations = powerOfAttorneys.stream()
                .filter(p -> userId.equals(p.getGrantee()))
                .map(this::getAuthorizationInformation)
                .collect(Collectors.toList());

        authorizationInformation.setAggregatedInformations(aggregatedInformations);
        return authorizationInformation;
    }

    private AggregatedInformation getAuthorizationInformation(PowerOfAttorney powerOfAttorney) {
        final AggregatedInformation aggregatedInformation = new AggregatedInformation();
        aggregatedInformation.setId(powerOfAttorney.getId());
        aggregatedInformation.setGrantor(powerOfAttorney.getGrantor());
        aggregatedInformation.setAccountNumber(powerOfAttorney.getAccount());
        aggregatedInformation.setDirection(powerOfAttorney.getDirection());
        aggregatedInformation.setAuthorizations(Arrays.asList(powerOfAttorney.getAuthorizations()));
        return aggregatedInformation;
    }

    @Override
    public List<PowerOfAttorney> getPowerOfAttorneys() {
        try {
            final PowerOfAttorney[] poas = restTemplate.getForObject(url, PowerOfAttorney[].class);
            log.info("Loading " + poas.length + " power of attorneys...");

            return Arrays.stream(poas)
                    .map(this::getPowerOfAttorney)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error loading power of attorneys.", e);
        }
        return new ArrayList<>(0);
    }

    private PowerOfAttorney getPowerOfAttorney(final PowerOfAttorney powerOfAttorney) {
        PowerOfAttorney poa = null;
        try {
            final String poaIdUrl = String.format("%s%s", url, powerOfAttorney.getId());
            poa = restTemplate.getForObject(poaIdUrl, PowerOfAttorney.class);
            log.info(poa.toString());
        } catch (Exception e) {
            log.error("Error loading power of attorney: id=" + powerOfAttorney.getId(), e);
        }
        return poa;
    }

}
