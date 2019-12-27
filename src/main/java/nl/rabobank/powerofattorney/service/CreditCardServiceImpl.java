package nl.rabobank.powerofattorney.service;

import nl.rabobank.powerofattorney.model.CreditCard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of {@link CreditCardService}.
 */
@Component
class CreditCardServiceImpl extends AbstractEntityServiceImpl<CreditCard, String> implements CreditCardService {

    @Value("${creditcards.url}")
    private String url;

    /**
     * Constructor.
     *
     * @param restTemplate {@link RestTemplate} instance.
     */
    CreditCardServiceImpl(final RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    protected String getUrl() {
        return url;
    }

    @Override
    public CreditCard getCreditCard(String id) {
        return getEntity(id, CreditCard.class);
    }
}
