package nl.rabobank.powerofattorney.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Abstract implementation of an entity E service.
 */
@Component
@Slf4j
abstract class AbstractEntityServiceImpl<E, I> {

    private final RestTemplate restTemplate;

    /**
     * Constructor.
     *
     * @param restTemplate {@link RestTemplate} instance.
     */
    AbstractEntityServiceImpl(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected abstract String getUrl();

    protected E getEntity(final I id, final Class<E> entityClass) {
        E entity = null;
        try {
            final String entityIdUrl = String.format("%s%s", getUrl(), id);
            entity = restTemplate.getForObject(entityIdUrl, entityClass);
            if (entity != null) {
                log.info(entity + " loaded.");
            }
        } catch (Exception e) {
            log.error("Error loading entity: id=" + id, e);
        }
        return entity;
    }

}
