package nl.rabobank.powerofattorney.service;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.powerofattorney.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of {@link AuthorizationService}.
 */
@Component
@Slf4j
class AuthorizationServiceImpl implements AuthorizationService {

    private final PowerOfAttorneyService powerOfAttorneyService;
    private final AccountService accountService;
    private final DebitCardService debitCardService;

    /**
     * Constructor.
     *
     * @param powerOfAttorneyService {@link PowerOfAttorneyService} instance.
     * @param accountService         {@link AccountService} instance.
     */
    AuthorizationServiceImpl(final PowerOfAttorneyService powerOfAttorneyService, final AccountService accountService, final DebitCardService debitCardService) {
        this.powerOfAttorneyService = powerOfAttorneyService;
        this.accountService = accountService;
        this.debitCardService = debitCardService;
    }

    @Override
    public AuthorizationInformation getAuthorizations(String userId) {
        final AuthorizationInformation authorizationInformation = new AuthorizationInformation();
        authorizationInformation.setUserId(userId);

        final List<PowerOfAttorney> powerOfAttorneys = powerOfAttorneyService.getPowerOfAttorneys();
        final List<AggregatedInformation> aggregatedInformations = powerOfAttorneys.stream()
                .filter(p -> userId.equals(p.getGrantee()))
                .map(this::getAuthorizationInformation)
                .filter(a -> Objects.nonNull(a.getAccount()))
                .filter(a -> Objects.isNull(a.getAccount().getEnded()))
                .collect(Collectors.toList());

        authorizationInformation.setAggregatedInformations(aggregatedInformations);
        return authorizationInformation;
    }

    private AggregatedInformation getAuthorizationInformation(PowerOfAttorney powerOfAttorney) {
        final AggregatedInformation aggregatedInformation = new AggregatedInformation();
        aggregatedInformation.setId(powerOfAttorney.getId());
        aggregatedInformation.setGrantor(powerOfAttorney.getGrantor());
        aggregatedInformation.setDirection(powerOfAttorney.getDirection());
        final String[] authorizations = Objects.requireNonNullElse(powerOfAttorney.getAuthorizations(), new String[0]);
        aggregatedInformation.setAuthorizations(Arrays.asList(authorizations));

        final Account account = accountService.getAccount(powerOfAttorney.getAccount());
        aggregatedInformation.setAccount(account);

        if (powerOfAttorney.getCards() != null) {
            aggregatedInformation.setDebitCards(new ArrayList<>(2));
            for (final Card card : powerOfAttorney.getCards()) {
                if ("DEBIT_CARD".equals(card.getType())) {
                    final DebitCard debitCard = debitCardService.getDebitCard(card.getId());
                    aggregatedInformation.getDebitCards().add(debitCard);
                }
            }
        }

        return aggregatedInformation;
    }

}
