package nl.rabobank.powerofattorney.service;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.powerofattorney.model.PowerOfAttorney;
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
class PowerOfAttorneyServiceImpl implements PowerOfAttorneyService {

    @Value("${powerofattorneys.url}")
    private String url;

    private final RestTemplate restTemplate;

    /**
     * Constructor.
     *
     * @param restTemplate {@link RestTemplate} instance.
     */
    PowerOfAttorneyServiceImpl(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<PowerOfAttorney> getPowerOfAttorneys() {
        try {
            final PowerOfAttorney[] poas = restTemplate.getForObject(url, PowerOfAttorney[].class);
            if (poas != null) {
                log.info("Loading " + poas.length + " power of attorneys...");

                return Arrays.stream(poas)
                        .map(this::getPowerOfAttorney)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
            }
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
            if (poa != null) {
                log.info(poa + " loaded.");
            }
        } catch (Exception e) {
            log.error("Error loading power of attorney: id=" + powerOfAttorney.getId(), e);
        }
        return poa;
    }

}
