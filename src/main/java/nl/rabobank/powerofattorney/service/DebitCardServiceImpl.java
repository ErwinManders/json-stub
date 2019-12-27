package nl.rabobank.powerofattorney.service;

import nl.rabobank.powerofattorney.model.DebitCard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of {@link DebitCardService}.
 */
class DebitCardServiceImpl extends AbstractEntityServiceImpl<DebitCard, String> implements DebitCardService {

    @Value("${debitcards.url}")
    private String url;

    /**
     * Constructor.
     *
     * @param restTemplate {@link RestTemplate} instance.
     */
    DebitCardServiceImpl(final RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    protected String getUrl() {
        return url;
    }

    @Override
    public DebitCard getDebitCard(String id) {
        return getEntity(id, DebitCard.class);
    }
}
